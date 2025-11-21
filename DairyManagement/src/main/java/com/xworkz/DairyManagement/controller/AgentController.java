package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.dto.AgentDto;
import com.xworkz.DairyManagement.entity.AgentBankEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;

import com.xworkz.DairyManagement.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@Slf4j
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/agentDashboard")
    public String agentDashboard(HttpSession session, Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String search) {

        // Check if admin is logged in (optional, if required)
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLoginForm";
        }

        List<AgentDto> agents;
        long totalAgents;

        if (search != null && !search.trim().isEmpty()) {
            // 🔍 Search filter applied
            agents = agentService.searchAgents(search.trim(), page, size);
            totalAgents = agentService.getAgentSearchCount(search.trim());
            model.addAttribute("search", search);
        } else {
            // 📄 Normal pagination
            agents = agentService.getAllAgents(page, size);
            totalAgents = agentService.getAgentCount();
        }

        int totalPages = (int) Math.ceil((double) totalAgents / size);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("agents", agents);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalRecords", totalAgents);

        return "agentDashboard";
    }

    @PostMapping("/registerAgents")
    public String registerAgents(@ModelAttribute AgentDto agentDto, RedirectAttributes redirectAttributes){
        agentService.registerAgent(agentDto);
        redirectAttributes.addFlashAttribute("successMessage","Agent Registered Successfully");

        return "redirect:/agentDashboard";
    }

   @GetMapping("/editAgent")
    public String editAgent(@RequestParam("agentId") Integer id, Model model){
        AgentDto agent=agentService.findById(id);
       log.info("id in Controller: {}", id);
       log.info("agent: {}", agent);
        if(agent==null){
            return "redirect:/agentDashboard";
        }
        model.addAttribute("agent",agent);
        return "editAgentProfile";

   }

   @PostMapping("/updateAgent")
    public String updateAgent(@ModelAttribute AgentDto agentDto, RedirectAttributes redirectAttributes){
       boolean updated= agentService.updateAgent(agentDto);
       if(updated){
           redirectAttributes.addFlashAttribute("successMessage","Agent Updated Successfully");
       }else {
           redirectAttributes.addFlashAttribute("errorMessage","Agent not Updated Successfully");

       }
        return "redirect:/agentDashboard";
    }

    @GetMapping("deleteAgent")
    public String deleteAgent(@RequestParam("agentId") Integer id, RedirectAttributes redirectAttributes){
         boolean deleted=agentService.deleteAgent(id);
         if (deleted){
             redirectAttributes.addFlashAttribute("successMessage","Agent Deleted Successfully");
         }else {
             redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Agent");
         }
        return "redirect:/agentDashboard";
    }

    @GetMapping("/agentLogin")
    public String agentLogin(){
        return "agentLogin";
    }

    @PostMapping("/validateAgentEmail")
    @ResponseBody
    public String validateAgentEmail(@RequestParam("emailId") String emailId) {
        boolean exists = agentService.isEmailRegistered(emailId);
        return exists ? "valid" : "invalid";
    }

    // ✅ Step 2: Send OTP to Registered Email
    @PostMapping("/sendAgentOtp")
    @ResponseBody
    public String sendAgentOtp(@RequestParam("emailId") String emailId, HttpSession session) {
        try {
            String otp = agentService.generateOtp();
            boolean sent = agentService.sendOtpToEmail(emailId, otp);

            if (sent) {
                session.setAttribute("agentOtp", otp);
                session.setAttribute("agentEmail", emailId);
                session.setMaxInactiveInterval(300); // 5 minutes expiry
                return "sent";
            } else {
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    // ✅ Step 3: Verify OTP and Login
    @PostMapping("/agentLogin")
    @ResponseBody
    public String verifyOtp(@RequestParam("emailId") String emailId,
                            @RequestParam("otp") String otp,
                            HttpSession session) {

        String storedOtp = (String) session.getAttribute("agentOtp");
        String storedEmail = (String) session.getAttribute("agentEmail");

        if (storedOtp != null && storedEmail != null &&
                storedEmail.equals(emailId) && storedOtp.equals(otp)) {

            AgentEntity agent = agentService.getAgentByEmail(emailId);
            session.setAttribute("loggedInAgent", agent);

            // Clear OTP after success
            session.removeAttribute("agentOtp");
            session.removeAttribute("agentEmail");

            return "success";
        } else {
            return "invalid";
        }
    }

    @GetMapping("/agentLoginSuccess")
    public String showAgentDashboard(HttpSession session, Model model) {
        AgentEntity loggedInAgent = (AgentEntity) session.getAttribute("loggedInAgent");
        if (loggedInAgent == null) {
            return "redirect:/agentLogin";
        }
        model.addAttribute("loggedInAgent", loggedInAgent);
        return "agentLoginSuccess";
    }
//
//    @PostMapping("/updateAgentProfile")
//    public String updateAgentProfile(@ModelAttribute AgentEntity updatedAgent,
//                                     HttpSession session,
//                                     RedirectAttributes redirectAttributes) {
//
//        AgentEntity currentAgent = (AgentEntity) session.getAttribute("loggedInAgent");
//        if (currentAgent == null) {
//            return "redirect:/agentLogin";
//        }
//
//        // Only editable fields
//        currentAgent.setFirstName(updatedAgent.getFirstName());
//        currentAgent.setLastName(updatedAgent.getLastName());
//        currentAgent.setAddress(updatedAgent.getAddress());
//
//       boolean updated = agentService.updateAgentEntity(currentAgent);
//
//        if (updated) {
//            session.setAttribute("loggedInAgent", currentAgent);
//            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update profile. Please try again.");
//        }
//
//        return "redirect:/agentLoginSuccess";
//    }
//
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/agentLogin";
    }


    @PostMapping("/updateAgentProfile")
    public String updateAgentProfile(@ModelAttribute AgentEntity updatedAgent,
                                     @RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes) {
        AgentEntity currentAgent = (AgentEntity) session.getAttribute("loggedInAgent");
        if (currentAgent == null) {
            return "redirect:/agentLogin";
        }

        try {
            // Update only editable fields
            currentAgent.setFirstName(updatedAgent.getFirstName());
            currentAgent.setLastName(updatedAgent.getLastName());
            currentAgent.setAddress(updatedAgent.getAddress());

            // Handle profile photo upload
            if (profilePhoto != null && !profilePhoto.isEmpty()) {
                // ✅ Get the real path of the Tomcat 'uploads' directory
                String uploadsDir = request.getServletContext().getRealPath("/uploads/");
                File uploadDirFile = new File(uploadsDir);
                if (!uploadDirFile.exists()) uploadDirFile.mkdirs();

                // Save file with unique name (agentId + original name)
                String fileName = currentAgent.getAgentId() + "_" + profilePhoto.getOriginalFilename();
                File destination = new File(uploadDirFile, fileName);
                profilePhoto.transferTo(destination);

                // Save the web-accessible path in DB (not physical path)
                currentAgent.setProfilePhotoPath(request.getContextPath() + "/uploads/" + fileName);
            }

            boolean updated = agentService.updateAgentEntity(currentAgent);
            if (updated) {
                session.setAttribute("loggedInAgent", currentAgent);
                redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Profile update failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating profile!");
        }

        return "redirect:/agentLoginSuccess";
    }

 /*   @GetMapping("/agentBankDetailsInfo")
    public String showAgentBankDetailsInfo(@RequestParam("agentId") Integer id,HttpSession session, Model model) {
     //   AgentEntity loggedInAgent = (AgentEntity) session.getAttribute("loggedInAgent");
      *//*  if (loggedInAgent == null) {
            return "redirect:/agentLogin";
        }*//*
        AgentBankEntity bankDetails = agentService.getBankDetailsByAgentId(id);

        log.info("bankDetails: {}", bankDetails);
     *//*   if (bankDetails == null) {
            model.addAttribute("msg","Bank details not added yet");
            return "redirect:/agentBankDetailsInfo";
        }*//*

        //model.addAttribute("loggedInAgent", loggedInAgent);
        return "agentBankDetailsInfo";
    }*/

    @GetMapping("/agentBankDetailsInfo")
    public String showAgentBankDetailsInfo(@RequestParam("agentId") Integer id, HttpSession session, Model model) {
        AgentBankEntity bankDetails = agentService.getBankDetailsByAgentId(id);

        log.info("bankDetails: {}", bankDetails);

        model.addAttribute("bankDetails", bankDetails);

        return "agentBankDetailsInfo";
    }


    @PostMapping("/updateAgentBankDetails")
    public String updateAgentBankDetails(@ModelAttribute AgentBankEntity bankEntity,RedirectAttributes redirectAttributes) {

      boolean updated=  agentService.updateBankDetails(bankEntity);
        if (updated) {
            redirectAttributes.addFlashAttribute("successMessage", "Bank Details updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Bank Details update failed!");
        }

        return "redirect:/agentBankDetailsInfo?agentId=" + bankEntity.getAgentId();
    }




}

