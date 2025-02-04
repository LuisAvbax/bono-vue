package com.example.bono_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "custom_fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityType;
    private String fieldName;
    private String fieldType;
}
