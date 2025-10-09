package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.dto.AgentDto;
import com.xworkz.DairyManagement.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
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
        System.out.println("id in Controller: "+id);
        System.out.println("agent: "+agent);
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

}

