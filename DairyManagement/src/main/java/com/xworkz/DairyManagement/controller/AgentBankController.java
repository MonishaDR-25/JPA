package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.dto.BankDto;
import com.xworkz.DairyManagement.entity.AgentBankEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;

import com.xworkz.DairyManagement.service.AgentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class AgentBankController {

    @Autowired
    AgentService agentService;

    @GetMapping("/agentBankDetails")
    public String showBankDetailsPage(HttpSession session, Model model) {
        AgentEntity agent = (AgentEntity) session.getAttribute("loggedInAgent");
        if (agent == null) {
            return "redirect:/agentLogin";
        }
        
        // Check if bank details exist
        AgentBankEntity bankDetails = agentService.getBankDetailsByAgentId(agent.getAgentId());
        if (bankDetails != null) {
            return "redirect:/viewBankDetails";
        }
        
        model.addAttribute("loggedInAgent", agent);
        model.addAttribute("bankDto", new BankDto());
        return "agentBankDetails";
    }

    @GetMapping("/viewBankDetails")
    public String viewBankDetails(HttpSession session, Model model) {
        AgentEntity agent = (AgentEntity) session.getAttribute("loggedInAgent");
        if (agent == null) {
            return "redirect:/agentLogin";
        }
        
        AgentBankEntity bankDetails = agentService.getBankDetailsByAgentId(agent.getAgentId());
        if (bankDetails == null) {
            return "redirect:/agentBankDetails";
        }

        model.addAttribute("bankDetails", bankDetails);
        model.addAttribute("loggedInAgent", agent);
      //  session.setAttribute("loggedInAgent", agent);
        return "viewBankDetails";
    }

    @PostMapping("/saveBankDetails")
    public String saveBankDetails(@ModelAttribute BankDto bankDto, HttpSession session, RedirectAttributes redirectAttributes) {
        AgentEntity agent = (AgentEntity) session.getAttribute("loggedInAgent");
        if (agent == null) {
            return "redirect:/agentLogin";
        }
        
        bankDto.setAgentId(agent.getAgentId());
        boolean saved = agentService.saveBankDetails(bankDto);
        if (saved) {
            redirectAttributes.addFlashAttribute("successMessage", "Bank details added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to save bank details. Please try again.");
        }
        return "redirect:/viewBankDetails";
    }
}
