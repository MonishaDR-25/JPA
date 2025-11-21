package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AgentDto;
import com.xworkz.DairyManagement.dto.BankDto;
import com.xworkz.DairyManagement.entity.AgentBankEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;
import com.xworkz.DairyManagement.repository.AgentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public List<AgentDto> getAllAgents(int page, int size) {
        int start = (page - 1) * size;
        List<AgentEntity> agentEntities = agentRepository.getAllAgents(start, size);
        List<AgentDto> agentDtos = new ArrayList<>();

        for (AgentEntity entity : agentEntities) {
            AgentDto dto = new AgentDto();
            BeanUtils.copyProperties(entity, dto);
            agentDtos.add(dto);
        }

        return agentDtos;
    }


    @Override
    public void registerAgent(AgentDto agentDto) {
        AgentEntity agentEntity=new AgentEntity();
        BeanUtils.copyProperties(agentDto,agentEntity);
        agentRepository.register(agentEntity);
    }

    @Override
    public AgentDto findById(Integer id) {
        AgentEntity agentEntity=agentRepository.findById(id);
        if(agentEntity==null){
            return null;
        }
        AgentDto agentDto=new AgentDto();
        BeanUtils.copyProperties(agentEntity,agentDto);
        return agentDto;
    }

    @Override
    public boolean updateAgent(AgentDto agentDto) {
        AgentEntity agentEntity=agentRepository.findById(agentDto.getAgentId());
        if(agentEntity==null){
            return false;
        }
        BeanUtils.copyProperties(agentDto,agentEntity);
        agentRepository.update(agentEntity);
        return true;
    }

    @Override
    public boolean deleteAgent(Integer id) {
        AgentEntity agentEntity=agentRepository.findById(id);
        if (agentEntity==null){
            return false;
        }
        agentRepository.delete(id);
        return true;
    }

    @Override
    public long getAgentCount() {
        return agentRepository.getAgentCount();
    }

    @Override
    public List<AgentDto> searchAgents(String trim, int page, int size) {
        int start = (page - 1) * size;
        List<AgentEntity> agentEntities = agentRepository.searchAgents(trim, start, size);
        List<AgentDto> agentDtos = new ArrayList<>();

        for (AgentEntity entity : agentEntities) {
            AgentDto dto = new AgentDto();
            BeanUtils.copyProperties(entity, dto);
            agentDtos.add(dto);
        }

        return agentDtos;
    }

    @Override
    public long getAgentSearchCount(String trim) {

        return agentRepository.getAgentSearchCount(trim);
    }


public AgentEntity getAgentByPhoneNumber(String phoneNumber) {
        AgentEntity agentEntity=agentRepository.findByPhone(phoneNumber);
        if(agentEntity==null){
            return null;
        }
        return agentEntity;


    }

    @Override
    public AgentEntity findByPhone(String phoneNumber) {
        AgentEntity agentEntity=agentRepository.findByPhone(phoneNumber);
        if(agentEntity==null){
            return null;
        }
        return agentEntity;
    }

    public boolean isEmailRegistered(String email) {
        return agentRepository.isEmailRegistered(email);
    }

    public AgentEntity getAgentByEmail(String email) {
        AgentEntity opt = agentRepository.findByEmail(email);
        return opt;
    }

    public String generateOtp() {
        int otp = 100000 + new Random().nextInt(900000);
        return String.valueOf(otp);
    }

    public boolean sendOtpToEmail(String email, String otp) {
        System.out.println("Mock OTP sent: " + otp + " to " + email);
        SimpleMailMessage message = new SimpleMailMessage();
      //  message.setFrom("monishadr25@gmail.com");
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);
      //  mailSender.send(message);
        return true;
    }

    public AgentEntity getAgentByEmailId(String email) {
        return agentRepository.findByEmail(email);
    }

    @Override
    public boolean updateAgentEntity(AgentEntity currentAgent) {
        try {
            agentRepository.updateAgent(currentAgent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveBankDetails(BankDto bankDto) {
        AgentBankEntity bankEntity = new AgentBankEntity();
        BeanUtils.copyProperties(bankDto, bankEntity);

        agentRepository.saveBankDetails(bankEntity);

        return true;
    }

    @Override
    public AgentBankEntity getBankDetailsByAgentId(Integer agentId) {
        return agentRepository.getBankDetailsByAgentId(agentId);
    }

    @Override
    public boolean updateBankDetails(AgentBankEntity bankEntity) {
        agentRepository.updateBankDetails(bankEntity);
        return true;
    }


    public boolean updateAgentProfile(AgentEntity agentEntity) {
        try {
            agentRepository.updateAgent(agentEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
