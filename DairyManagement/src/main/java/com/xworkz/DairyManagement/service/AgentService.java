package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AgentDto;

import java.util.List;

public interface AgentService {

    List<AgentDto> getAllAgents(int page, int size);

    void registerAgent(AgentDto agentDto);

    AgentDto findById(Integer id);

    boolean updateAgent(AgentDto agentDto);

    boolean deleteAgent(Integer id);
}
