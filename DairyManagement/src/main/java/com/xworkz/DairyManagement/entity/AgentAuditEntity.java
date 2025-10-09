package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="agent_audit")
public class AgentAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="audit_id")
    private Integer agentId;

    @ManyToOne
    @JoinColumn(name="agent_id", referencedColumnName = "agent_id")
    private AgentEntity agent;

    @Column(name="agent_name")
    private String agentName;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;
}
