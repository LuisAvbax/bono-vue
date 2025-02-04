package com.example.bono_challenge.controller;

import com.example.bono_challenge.dto.CustomFieldDto;
import com.example.bono_challenge.dto.CustomFieldValueDto;
import com.example.bono_challenge.entity.CustomField;
import com.example.bono_challenge.entity.CustomFieldValue;
import com.example.bono_challenge.service.CustomFieldService;
import com.example.bono_challenge.service.CustomFieldValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entity")
@Tag(name = "Entity API", description = "APIs for managing Custom Fields and Custom Field Values")
public class EntityController {

    @Autowired
    private CustomFieldService customFieldService;

    @Autowired
    private CustomFieldValueService customFieldValueService;

    @Operation(
            summary = "Create a Custom Field",
            description = "Defines a new custom field for a specific entity type. For example, you can add fields like `total_co2` to `Facility`."
    )
    @PostMapping("/custom-fields")
    public CustomFieldDto createCustomField(@RequestBody CustomFieldDto dto) {
        CustomField saved = customFieldService.createCustomField(dto);
        dto.setId(saved.getId());
        return dto;
    }

    @Operation(
            summary = "Get Custom Fields for an Entity Type",
            description = "Retrieves all custom fields defined for a specific entity type (e.g., `Customer`, `Facility`, etc.)."
    )
    @GetMapping("/{entityType}/custom-fields")
    public List<CustomFieldDto> getCustomFields(
            @Parameter(description = "The type of the entity to retrieve custom fields for (e.g., `Customer`, `Facility`).", example = "Facility")
            @PathVariable String entityType) {
        List<CustomField> fields = customFieldService.getCustomFieldsByEntityType(entityType);
        return fields.stream().map(f -> {
            CustomFieldDto result = new CustomFieldDto();
            result.setId(f.getId());
            result.setEntityType(f.getEntityType());
            result.setFieldName(f.getFieldName());
            result.setFieldType(f.getFieldType());
            return result;
        }).collect(Collectors.toList());
    }

    @Operation(
            summary = "Get All Custom Field Values",
            description = "Retrieves all custom field values for all entities. Useful for debugging or bulk checks."
    )
    @GetMapping("/")
    public List<CustomFieldValue> getAll() {
        return customFieldValueService.getAll();
    }

    @Operation(
            summary = "Create or Update a Custom Field Value",
            description = "Assigns a value to a custom field for a specific entity. If the field value already exists, it will be updated."
    )
    @PostMapping("/custom-field-values")
    public CustomFieldValueDto saveCustomFieldValue(@RequestBody CustomFieldValueDto dto) {
        CustomFieldValue savedEntity = customFieldValueService.createOrUpdateCustomFieldValue(dto);
        dto.setId(savedEntity.getId());
        return dto;
    }

    @Operation(
            summary = "Get Custom Field Values for a Specific Entity",
            description = "Retrieves all custom field values assigned to a specific entity. For example, you can retrieve all custom field values for a Facility with `id=1`."
    )
    @GetMapping("/{entityType}/{entityId}/custom-field-values")
    public List<CustomFieldValueDto> getCustomFieldValues(
            @Parameter(description = "The type of the entity (e.g., `Customer`, `Facility`).", example = "Facility")
            @PathVariable String entityType,
            @Parameter(description = "The ID of the entity to retrieve custom field values for.", example = "1")
            @PathVariable Long entityId
    ) {
        List<CustomFieldValue> values = customFieldValueService.getValuesForEntity(entityType, entityId);
        return values.stream().map(v -> {
            CustomFieldValueDto dto = new CustomFieldValueDto();
            dto.setId(v.getId());
            dto.setEntityType(v.getEntityType());
            dto.setEntityId(v.getEntityId());
            dto.setFieldName(v.getFieldName());
            dto.setFieldValue(v.getFieldValue());
            return dto;
        }).collect(Collectors.toList());
    }
}
