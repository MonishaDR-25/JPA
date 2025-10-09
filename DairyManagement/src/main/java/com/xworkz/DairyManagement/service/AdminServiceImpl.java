package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.entity.AdminEntity;
import com.xworkz.DairyManagement.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository repository;

    @Autowired
    JavaMailSender mailSender;

    public AdminServiceImpl(){
        System.out.println("Running AdminServiceImpl");
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void register(AdminDto adminDto) {

        AdminEntity entity=new AdminEntity();
        BeanUtils.copyProperties(adminDto,entity);

        String encodedPassword=passwordEncoder.encode(adminDto.getPassword());
        entity.setPassword(encodedPassword);
        repository.save(entity);
    }

    @Override
    public AdminEntity findByEmailEntity(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean checkPassword(String password, String password1) {
        return passwordEncoder.matches(password,password1) ;
    }

    @Override
    public void resetFailedAttempts(AdminEntity adminEntity) {
        adminEntity.setFailedAttempts(0);
        repository.updateAdmin(adminEntity);
    }

    @Override
    public void increaseFailedAttempts(AdminEntity adminEntity) {
        adminEntity.setFailedAttempts(adminEntity.getFailedAttempts()+1);
        repository.updateAdmin(adminEntity);
    }

    @Override
    public void lockAccount(AdminEntity adminEntity) {
        adminEntity.setAccountLocked(true);
        adminEntity.setLockedAt(LocalDateTime.now());
        repository.updateAdmin(adminEntity);
    }

    @Override
    public void generateUnlockToken(AdminEntity admin) {
        String token = UUID.randomUUID().toString();
        admin.setUnlockToken(token);
        admin.setUnlockTokenExpiry(LocalDateTime.now().plusMinutes(15)); // 15 min validity
        repository.updateAdmin(admin);
    }

    @Override
    public void sendUnlockEmail(AdminEntity admin) {
        //  String unlockLink = baseUrl + "/admin/unlock?token=" + admin.getUnlockToken();
        String unlockUrl = "http://localhost:9090/DairyManagement/unlock?token=" + admin.getUnlockToken();
        String subject = "Unlock Your HappyCow Admin Account";
        String body = "Hi " + admin.getAdminName() + ",\n\n"
                + "Your account has been locked due to 3 failed login attempts.\n"
                + "Click the link below to unlock and reset your password:\n\n"
                + unlockUrl + "\n\n"
                + "This link will expire in 15 minutes.\n\n"
                + "Regards,\nHappyCow Dairy Team";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(admin.getEmailId());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        log.info("Unlock email sent to {}", admin.getEmailId());
        log.info(body);
    }

    @Override
    public AdminEntity findByUnlockToken(String token) {
        AdminEntity adminEntity=repository.findByUnlockToken(token);
        return adminEntity;
    }

    @Override
    public void updateAdmin(AdminEntity loggedInAdmin) {
       repository.updateAdmin(loggedInAdmin);
    }

    @Override
    public AdminEntity getAdminById(Integer adminId) {
        return repository.findById(adminId);
    }
}
