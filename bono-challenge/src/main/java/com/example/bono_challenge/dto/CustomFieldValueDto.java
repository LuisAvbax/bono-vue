package com.example.bono_challenge.dto;

import lombok.Data;

@Data
public class CustomFieldValueDto {
    private Long id;
    private String entityType;
    private Long entityId;
    private String fieldName;
    private String fieldValue;
}