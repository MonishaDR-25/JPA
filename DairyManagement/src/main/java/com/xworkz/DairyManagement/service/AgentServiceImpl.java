package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AgentDto;
import com.xworkz.DairyManagement.entity.AgentEntity;
import com.xworkz.DairyManagement.repository.AgentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService{
    @Autowired
    AgentRepository agentRepository;
    @Override
    public List<AgentDto> getAllAgents(int page, int size) {
        List<AgentEntity> entity=agentRepository.findAllAgents();
        List<AgentDto> dtos=new ArrayList<>();

        for(AgentEntity agentEntity:entity){
            AgentDto agentDto=new AgentDto();
            BeanUtils.copyProperties(agentEntity,agentDto);
            dtos.add(agentDto);
        }
        return dtos;
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


}
