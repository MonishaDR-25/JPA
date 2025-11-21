package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.entity.AgentEntity;

import com.xworkz.DairyManagement.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
@Slf4j
public class AgentProfileController {


    @GetMapping("/agent/updateProfile")
    public String agentProfile(){
        return "agentLoginSuccess";
    }

    @Autowired
    AgentService agentService;

    // ✅ Load the profile page after login
    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        AgentEntity loggedInAgent = (AgentEntity) session.getAttribute("loggedInAgent");

        if (loggedInAgent == null) {
            return "redirect:/agentLogin.jsp"; // redirect to login if session expired
        }

        // Refresh data from DB
        AgentEntity dbAgent = agentService.getAgentByEmailId(loggedInAgent.getEmail());
        model.addAttribute("agent", dbAgent);
        return "agentLoginSuccess";
    }

    // ✅ Handle profile updates
    @PostMapping("/agent/updateProfile")
    public String updateProfile(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("address") String address,
                                @RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto,
                                HttpSession session,
                                Model model) {

        AgentEntity loggedInAgent = (AgentEntity) session.getAttribute("loggedInAgent");

        if (loggedInAgent == null) {
            return "redirect:/agentLogin.jsp";
        }

        // Update editable fields
        loggedInAgent.setFirstName(firstName);
        loggedInAgent.setLastName(lastName);
        loggedInAgent.setAddress(address);

        // ✅ Handle profile photo upload
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            try {
                String uploadDir = "C:/DairyManagement/profile-photos/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String filePath = uploadDir + loggedInAgent.getAgentId() + "_" + profilePhoto.getOriginalFilename();
                profilePhoto.transferTo(new File(filePath));
                System.out.println("Profile photo saved at: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ✅ Save updated data
        boolean updated = agentService.updateAgentProfile(loggedInAgent);

        // ✅ Refresh and show success message
        model.addAttribute("agent", loggedInAgent);
        model.addAttribute("updateSuccess", updated ? "Profile updated successfully!" : "Update failed. Try again.");
        return "agentLoginSuccess";
    }

}
