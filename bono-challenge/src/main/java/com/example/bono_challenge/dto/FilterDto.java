package com.example.bono_challenge.dto;

import lombok.Data;
import java.util.Map;

@Data
public class FilterDto {
    private String entity;  // e.g. "CarbonFootprint"
    private RelationshipDto relationship;
    private Map<String, String> conditions;
}
