package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AgentDto;
import com.xworkz.DairyManagement.dto.BankDto;
import com.xworkz.DairyManagement.entity.AgentBankEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;

import java.util.List;

public interface AgentService {
    AgentEntity getAgentByEmailId(String email);

    boolean updateAgentProfile(AgentEntity loggedInAgent);

    List<AgentDto> searchAgents(String trim, int page, int size);

    long getAgentSearchCount(String trim);

    List<AgentDto> getAllAgents(int page, int size);

    long getAgentCount();

    void registerAgent(AgentDto agentDto);

    AgentDto findById(Integer id);

    boolean updateAgent(AgentDto agentDto);

    boolean deleteAgent(Integer id);

    boolean isEmailRegistered(String emailId);

    String generateOtp();

    boolean sendOtpToEmail(String emailId, String otp);

    AgentEntity getAgentByEmail(String emailId);

    boolean updateAgentEntity(AgentEntity currentAgent);

    AgentEntity findByPhone(String phoneNumber);

    boolean saveBankDetails(BankDto bankDto);

    AgentBankEntity getBankDetailsByAgentId(Integer agentId);

    boolean updateBankDetails(AgentBankEntity bankEntity);
}
