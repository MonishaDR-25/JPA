package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.*;
import com.xworkz.DairyManagement.entity.*;
import com.xworkz.DairyManagement.repository.AdminRepository;
import com.xworkz.DairyManagement.repository.AgentRepository;
import com.xworkz.DairyManagement.repository.ProductCollectionRepository;
import com.xworkz.DairyManagement.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductCollectionServiceImpl implements ProductCollectionService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductCollectionRepository productCollectionRepository;

    @Override
    public List<ProductDto> getAllProductsByTypesOfMilk() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.getAllProductsByTypesOfMilk();
        for (ProductEntity productEntity : productEntities) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public void saveProductCollection(ProductCollectionDto productCollectionDto, AdminDto adminDto) {


        AdminEntity adminEntity = adminRepository.findByEmail(adminDto.getEmailId());

        AgentEntity agentEntity = agentRepository.findByPhone(productCollectionDto.getPhoneNumber());


        // --- Build entity
        ProductCollectionEntity entity = new ProductCollectionEntity();
        entity.setAgent(agentEntity);
        entity.setAdmin(adminEntity);
        entity.setTypeOfMilk(productCollectionDto.getTypeOfMilk());  // store the canonical product name
        entity.setPrice(productCollectionDto.getPrice());
        entity.setQuantity(productCollectionDto.getQuantity());
        entity.setTotalAmount(productCollectionDto.getTotalAmount());
        entity.setCollectedAt(LocalDate.now());

        productCollectionRepository.save(entity);

        ProductCollectionAuditEntity audit = new ProductCollectionAuditEntity();
        audit.setCreatedAt(LocalDateTime.now());
        audit.setCreatedBy(adminEntity.getAdminName()); // or admin.getEmailId()
        audit.setProductCollectionEntity(entity);

        // link back (not owning side, but keeps model consistent)
        entity.setProductCollectionAuditEntity(audit);

        // persist audit
        productCollectionRepository.saveAudit(audit);



    }

    @Override
    public List<ProductCollectionDto> getAllProductsByCollectionDate(String date) {
        List<ProductCollectionEntity> collections;

        if (date == null ) {
            collections = productCollectionRepository.getAllProductsByCollectionDate();

        } else {
            log.info("Date: " + date);
            LocalDate d = LocalDate.parse(date);
            log.info("Date d: " + d);
            collections = productCollectionRepository.findByCollectedDate(d);
        }

        List<ProductCollectionDto> list = new ArrayList<>();
        for (ProductCollectionEntity pc : collections) {
            ProductCollectionDto dto = new ProductCollectionDto();
            // copy simple fields
            dto.setProductCollectionId(pc.getProductCollectionId());
            dto.setTypeOfMilk(pc.getTypeOfMilk());
            dto.setPrice(pc.getPrice());
            dto.setQuantity(pc.getQuantity());
            dto.setTotalAmount(pc.getTotalAmount());
            dto.setCollectedAt(pc.getCollectedAt());

            // map AGENT -> AgentDTO (so JSP can show name/phone/email/address)
            if (pc.getAgent() != null) {
                AgentDto a = new AgentDto();
                a.setAgentId(pc.getAgent().getAgentId());
                a.setFirstName(pc.getAgent().getFirstName());
                a.setLastName(pc.getAgent().getLastName());
                a.setEmail(pc.getAgent().getEmail());
                a.setPhoneNumber(pc.getAgent().getPhoneNumber());
                a.setAddress(pc.getAgent().getAddress());
                dto.setAgent(a);

                // also keep phoneNumber in DTO if your JSP uses dto.phoneNumber
                dto.setPhoneNumber(pc.getAgent().getPhoneNumber());
            }

            // map ADMIN -> AdminDTO (for navbar or if you want to show who recorded it)
            if (pc.getAdmin() != null) {
                AdminDto ad = new AdminDto();
                ad.setAdminId(pc.getAdmin().getAdminId());
                ad.setAdminName(pc.getAdmin().getAdminName());
                ad.setEmailId(pc.getAdmin().getEmailId());
                ad.setPhoneNumber(pc.getAdmin().getPhoneNumber());
                dto.setAdmin(ad);
            }

            list.add(dto);
        }
        return list;
    }
    @Override
    public ProductCollectionAgentDto getDetailsDTO(Integer id) {
        // Make sure repo method uses the entity-graph version
        ProductCollectionEntity pc = productCollectionRepository.findByIdWithRelations(id);



        log.info("pc id: {}", pc.getProductCollectionId()); // safe log

        AgentEntity a = pc.getAgent(); // already loaded by graph

        ProductCollectionAgentDto dto = new ProductCollectionAgentDto();
        dto.setProductCollectionId(pc.getProductCollectionId());

        // Agent
        String first = a != null && a.getFirstName() != null ? a.getFirstName() : "";
        String last  = a != null && a.getLastName()  != null ? a.getLastName()  : "";
        dto.setAgentName((first + " " + last).trim());
        dto.setAgentEmail(a != null ? nullToEmpty(a.getEmail())       : "");
        dto.setAgentPhone(a != null ? nullToEmpty(a.getPhoneNumber()) : "");
        dto.setAgentAddress(a != null ? nullToEmpty(a.getAddress())   : "");

        // Collection
        dto.setTypeOfMilk(pc.getTypeOfMilk());
        dto.setPrice(pc.getPrice());
        dto.setQuantity(pc.getQuantity());
        dto.setTotalAmount(pc.getTotalAmount());

        log.info("dto built for collection {}", dto.getProductCollectionId());
        return dto;
    }
    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

}