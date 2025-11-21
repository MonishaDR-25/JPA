package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.AgentBankEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;

import java.util.List;
import java.util.Optional;

public interface AgentRepository {
    List<AgentEntity> getAllAgents(int start, int size);

    void register(AgentEntity agentEntity);

    AgentEntity findById(Integer id);

    void update(AgentEntity agentEntity);

    void delete(Integer id);

    long getAgentCount();

    List<AgentEntity> searchAgents(String trim, int start, int size);

    long getAgentSearchCount(String trim);

    AgentEntity findByPhone(String phoneNumber);

    boolean isEmailRegistered(String email);

    AgentEntity findByEmail(String email);

    void updateAgent(AgentEntity agentEntity);

    void saveBankDetails(AgentBankEntity bankEntity);

    AgentBankEntity getBankDetailsByAgentId(int agentId);

    boolean updateBankDetails(AgentBankEntity bankEntity);
}
