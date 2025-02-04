package com.example.bono_challenge.dto;

import lombok.Data;

@Data
public class RelationshipDto {
    private String relatedEntity; // e.g. "Facility"
    private String foreignKey;    // e.g. "facility_id"
    private String joinField;     // e.g. "id"
}
