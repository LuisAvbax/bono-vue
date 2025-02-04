package com.example.bono_challenge.dto;

import lombok.Data;

@Data
public class CustomFieldDto {
    private Long id;
    private String entityType;
    private String fieldName;
    private String fieldType;
}