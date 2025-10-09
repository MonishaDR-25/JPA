package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.AgentEntity;

import java.util.List;

public interface AgentRepository {
    public List<AgentEntity> findAllAgents();

    void register(AgentEntity agentEntity);

    AgentEntity findById(Integer id);

    void update(AgentEntity agentEntity);

    void delete(Integer id);
}
