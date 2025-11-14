package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.entity.AdminEntity;
import com.xworkz.DairyManagement.repository.AdminRepository;
import com.xworkz.DairyManagement.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
//@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("/adminLoginForm")
    public String adminLoginForm(){
        log.info("Home page requested");
        log.debug("Debugging info: entering home method");
        return "adminLoginForm";
    }

//    @PostMapping("/adminLogin")
//    public String adminLogin(@RequestParam String emailId,@RequestParam String password){
//
//    }

    @PostMapping("/adminLogin")
    public String adminLoginProcess(@RequestParam String emailId,
                                    @RequestParam String password,
                                    HttpSession session,
                                    Model model) {
        log.info("Admin login attempt for email: {}", emailId);

        AdminEntity adminEntity = adminService.findByEmailEntity(emailId);



        if (adminEntity == null) {
            log.warn("Login failed - no account found for email: {}", emailId);
            model.addAttribute("errorMessage", "Invalid email or password");
            return "adminLoginForm";
        }

        // 🚨 If account is already locked
        if (adminEntity.isAccountLocked()) {
            log.warn("Login attempt on locked account: {}", emailId);
            return "accountLocked"; // redirect to locked page
        }

        // ✅ Check password (you can replace with BCrypt check if using hashed passwords)
        boolean passwordMatches = adminService.checkPassword(password, adminEntity.getPassword());

        if (passwordMatches) {
            adminService.resetFailedAttempts(adminEntity);

            AdminDto adminDTO = new AdminDto();
            adminDTO.setAdminId(adminEntity.getAdminId());
            adminDTO.setAdminName(adminEntity.getAdminName());
            adminDTO.setEmailId(adminEntity.getEmailId());
            adminDTO.setPhoneNumber(adminEntity.getPhoneNumber());
            adminDTO.setProfilePicPath(adminEntity.getProfilePicPath());

//            adminDTO.setProfilePicture(adminEntity.getProfilePicture());
//            adminDTO.setProfilePictureContentType(adminEntity.getProfilePictureContentType());
            // copy other fields if needed

//            auditService.logAdminLogin(adminEntity);
            //  log.info("Login successful for email: {}", email);
            session.setAttribute("loggedInAdmin", adminDTO);
            model.addAttribute("loggedInAdmin", adminDTO);

//            log.info("Login successful for email: {} in {}ms",
//                    email, System.currentTimeMillis() - startTime);
            log.info("Login successful for email: {}", adminDTO);

            return "adminDashboard";
        } else {
            // ❌ Wrong password → increment failed attempts
            adminService.increaseFailedAttempts(adminEntity);

            if (adminEntity.getFailedAttempts() >= 3) {
                adminService.lockAccount(adminEntity);
                log.warn("Account locked due to 3 failed attempts: {}", emailId);
                return "accountLocked"; // locked page
            }

            log.warn("Invalid password attempt {} for email: {}", adminEntity.getFailedAttempts(), emailId);
            model.addAttribute("errorMessage", "Invalid email or password, attempt left: "+(3-adminEntity.getFailedAttempts()));
            return "adminLoginForm";
        }
    }

    @PostMapping("/sendUnlockLink")
    public String sendUnlockLink(@RequestParam String email, Model model) {
        log.info("Unlock request for email: {}", email);

        AdminEntity admin = adminService.findByEmailEntity(email);

        if (admin == null) {
            log.warn("Unlock request failed - no account found for email: {}", email);
            model.addAttribute("message", "No account found with this email.");
            return "accountLocked";
        }

        if (!admin.isAccountLocked()) {
            model.addAttribute("message", "This account is not locked. You can try logging in.");
            return "accountLocked";
        }

        // ✅ Generate token and send email
        adminService.generateUnlockToken(admin);
        adminService.sendUnlockEmail(admin);

        model.addAttribute("message", "An unlock link has been sent to your email.");
        return "accountLocked";
    }

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/unlock")
    public String unlockAccount(@RequestParam String token, Model model) {
        AdminEntity admin = adminService.findByUnlockToken(token);

        if (admin == null || admin.getUnlockTokenExpiry().isBefore(LocalDateTime.now())) {
            model.addAttribute("message", "Invalid or expired unlock link.");
            return "accountLocked";
        }

        // ✅ Unlock and redirect to reset password page
        admin.setAccountLocked(false);
        admin.setFailedAttempts(0);
        admin.setUnlockToken(null);
        admin.setUnlockTokenExpiry(null);
        adminRepository.updateAdmin(admin);

        model.addAttribute("email", admin.getEmailId());
        return "resetPassword"; // JSP where admin sets a new password
    }


    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {

        AdminEntity admin = adminService.findByEmailEntity(email);

        if (admin == null) {
            model.addAttribute("message", "Invalid request. User not found.");
            return "resetPassword";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Passwords do not match.");
            model.addAttribute("email", email);
            return "resetPassword";
        }

        // ✅ Save new password (use BCrypt for security)
        String hashedPassword = new BCryptPasswordEncoder().encode(password);
        admin.setPassword(hashedPassword);
        admin.setConfirmPassword(confirmPassword);

        admin.setAccountLocked(false);
        admin.setFailedAttempts(0);
        admin.setUnlockToken(null);
        admin.setUnlockTokenExpiry(null);

        adminRepository.updateAdmin(admin);

        model.addAttribute("message", "Password reset successfully. Please log in with your new password.");
        return "resetSuccess";
    }


    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("adminName") String adminName,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("emailId") String emailId,
                                @RequestParam("profilePicPath") MultipartFile profilePicPath,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes) throws IOException {

        // 1. Get logged-in admin DTO from session
        AdminDto loggedInAdminDto = (AdminDto) request.getSession().getAttribute("loggedInAdmin");
        if (loggedInAdminDto == null) {
            return "redirect:/adminLogin";
        }

        // 2. Fetch entity from DB using ID
        AdminEntity loggedInAdmin = adminService.getAdminById(loggedInAdminDto.getAdminId());

        // 3. Update entity fields
        loggedInAdmin.setAdminName(adminName);
        loggedInAdmin.setPhoneNumber(Long.valueOf(phoneNumber));
        loggedInAdmin.setEmailId(emailId);

        // 4. Handle profile picture upload
        if (!profilePicPath.isEmpty()) {
            String uploadDir = request.getServletContext().getRealPath("/images/");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + profilePicPath.getOriginalFilename();
            File filePath = new File(uploadDir, fileName);

            profilePicPath.transferTo(filePath);

            loggedInAdmin.setProfilePicPath("images/" + fileName);
        }

        // 5. Save updated entity
        adminService.updateAdmin(loggedInAdmin);

        // 6. Update session DTO
        AdminDto updatedDto = new AdminDto();
        BeanUtils.copyProperties(loggedInAdmin, updatedDto); // copies fields from entity to DTO

        request.getSession().setAttribute("loggedInAdmin", updatedDto);
        log.info("Updated Dto "+updatedDto);

        redirectAttributes.addFlashAttribute("successMsg", "Profile updated successfully!");
        return "redirect:/adminDashboard";
    }

  /*  @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("adminName") String adminName,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("emailId") String emailId,
                                @RequestParam("profilePicPath") MultipartFile profilePicPath,
                                HttpServletRequest request,
                                Model model) throws IOException {

        // Get logged-in admin (from session for example)
        AdminEntity loggedInAdmin = (AdminEntity) request.getSession().getAttribute("loggedInAdmin");

        if (loggedInAdmin == null) {
            return "redirect:/adminLoginForm";
        }

        loggedInAdmin.setAdminName(adminName);
        loggedInAdmin.setPhoneNumber(Long.valueOf(phoneNumber));
        loggedInAdmin.setEmailId(emailId);

        // Handle profile picture upload
        if (!profilePicPath.isEmpty()) {
            String uploadDir = request.getServletContext().getRealPath("/images/");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + profilePicPath.getOriginalFilename();
            File filePath = new File(uploadDir, fileName);

            profilePicPath.transferTo(filePath);

            loggedInAdmin.setProfilePicPath("images/" + fileName);
        }

        // Save to DB
        adminService.updateAdmin(loggedInAdmin);

        // Update session with latest data
        request.getSession().setAttribute("loggedInAdmin", loggedInAdmin);

        model.addAttribute("successMsg", "Profile updated successfully!");
        return "redirect:/adminDashboard"; // dashboard.jsp
    }*/

    @GetMapping("/adminDashboard")
    public String showAdminDashboard(HttpServletRequest request, Model model) {
        AdminDto loggedInAdmin = (AdminDto) request.getSession().getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLoginForm";
        }
        model.addAttribute("loggedInAdmin", loggedInAdmin);
        model.addAttribute("successMsg", "Profile updated successfully!");

        return "adminDashboard"; // This should be the JSP file name
    }

    @GetMapping("/adminLogout")
    public String adminLogout(HttpSession session,RedirectAttributes redirectAttributes) {
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLoginForm";
        }
        session.invalidate();
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully!");
        return "redirect:/adminLoginForm";
    }


}
