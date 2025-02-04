package com.example.bono_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String targetEntity;
    private String targetField;
    private String operation;

    private String sourceEntity;
    private String sourceField;

    @Column(columnDefinition = "TEXT")
    private String filterJson;
}
