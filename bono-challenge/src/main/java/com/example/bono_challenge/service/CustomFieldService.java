package com.example.bono_challenge.service;

import com.example.bono_challenge.dto.CustomFieldDto;
import com.example.bono_challenge.entity.CustomField;
import com.example.bono_challenge.repository.CustomFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomFieldService {

    @Autowired
    private CustomFieldRepository customFieldRepository;

    public CustomField createCustomField(CustomFieldDto dto) {
        CustomField customField = CustomField.builder()
                .entityType(dto.getEntityType())
                .fieldName(dto.getFieldName())
                .fieldType(dto.getFieldType())
                .build();

        return customFieldRepository.save(customField);
    }

    public List<CustomField> getCustomFieldsByEntityType(String entityType) {
        return customFieldRepository.findByEntityType(entityType);
    }
}
