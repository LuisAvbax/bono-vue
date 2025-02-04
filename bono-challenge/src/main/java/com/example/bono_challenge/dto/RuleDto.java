package com.example.bono_challenge.dto;

import lombok.Data;

@Data
public class RuleDto {
    private Long id;
    private String targetEntity;
    private String targetField;
    private String operation;
    private SourceDto source;
    private FilterDto filter;

}
