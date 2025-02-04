package com.example.bono_challenge.service;

import com.example.bono_challenge.dto.CustomFieldValueDto;
import com.example.bono_challenge.entity.CustomFieldValue;
import com.example.bono_challenge.repository.CustomFieldValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomFieldValueService {

    @Autowired
    private CustomFieldValueRepository customFieldValueRepository;

    public CustomFieldValue createOrUpdateCustomFieldValue(CustomFieldValueDto dto) {
        CustomFieldValue existing = customFieldValueRepository.findByEntityTypeAndEntityIdAndFieldName(
                dto.getEntityType(), dto.getEntityId(), dto.getFieldName()
        );

        if (existing != null) {
            existing.setFieldValue(dto.getFieldValue());
            return customFieldValueRepository.save(existing);
        }
        CustomFieldValue newValue = CustomFieldValue.builder()
                .entityType(dto.getEntityType())
                .entityId(dto.getEntityId())
                .fieldName(dto.getFieldName())
                .fieldValue(dto.getFieldValue())
                .build();
        return customFieldValueRepository.save(newValue);
    }

    public List<CustomFieldValue> getValuesForEntity(String entityType, Long entityId) {
        return customFieldValueRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }


    public List<CustomFieldValue> getAll(){
        return customFieldValueRepository.findAll();
    }
}
