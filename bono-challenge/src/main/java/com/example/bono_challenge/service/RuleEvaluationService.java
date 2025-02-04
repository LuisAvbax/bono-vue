package com.example.bono_challenge.service;

import com.example.bono_challenge.dto.CustomFieldValueDto;
import com.example.bono_challenge.entity.CarbonFootprint;
import com.example.bono_challenge.entity.Facility;
import com.example.bono_challenge.entity.Rule;
import com.example.bono_challenge.entity.CustomFieldValue;
import com.example.bono_challenge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RuleEvaluationService {



    @Autowired
    private CustomFieldValueService customFieldValueService;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private CarbonFootprintRepository carbonFootprintRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Async
    public void evaluateRuleAsync(Rule rule) {
        switch (rule.getOperation()) {
            case "sum":
                evaluateSum(rule);
                break;
            case "max":
                evaluateMax(rule);
                break;
            case "min":
                evaluateMin(rule);
                break;
            case "equals":
                evaluateEquals(rule);
                break;
            default:
                break;
        }
    }

    private void evaluateSum(Rule rule) {
        if ("Facility".equalsIgnoreCase(rule.getTargetEntity())) {
            List<Facility> facilities = facilityRepository.findAll();

            for (Facility facility : facilities) {
                List<CarbonFootprint> cfs = carbonFootprintRepository.findAll().stream()
                        .filter(cf -> cf.getFacilityId().equals(facility.getId()))
                        .toList();
                double sumValue = cfs.stream()
                        .mapToDouble(cf -> {
                            if ("co2".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getCo2() != null ? cf.getCo2() : 0.0;
                            } else if ("input_amount".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getInputAmount() != null ? cf.getInputAmount() : 0.0;
                            }
                            return 0.0;
                        })
                        .sum();
                CustomFieldValueDto dto = new CustomFieldValueDto();
                dto.setEntityType(rule.getTargetEntity()); // "Facility"
                dto.setEntityId(facility.getId());
                dto.setFieldName(rule.getTargetField()); // Ej. "total_co2"
                dto.setFieldValue(String.valueOf(sumValue));
                customFieldValueService.createOrUpdateCustomFieldValue(dto);
            }
        }
    }
    private void evaluateMax(Rule rule) {
        if ("Facility".equalsIgnoreCase(rule.getTargetEntity())) {
            List<Facility> facilities = facilityRepository.findAll();

            for (Facility facility : facilities) {
                List<CarbonFootprint> cfs = carbonFootprintRepository.findAll().stream()
                        .filter(cf -> cf.getFacilityId().equals(facility.getId()))
                        .toList();

                OptionalDouble maxOptional = cfs.stream()
                        .mapToDouble(cf -> {
                            if ("co2".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getCo2() != null ? cf.getCo2() : 0.0;
                            } else if ("input_amount".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getInputAmount() != null ? cf.getInputAmount() : 0.0;
                            }
                            return 0.0;
                        })
                        .max();

                double maxValue = maxOptional.orElse(0.0);


                CustomFieldValueDto dto = new CustomFieldValueDto();
                dto.setEntityType(rule.getTargetEntity());
                dto.setEntityId(facility.getId());
                dto.setFieldName(rule.getTargetField());
                dto.setFieldValue(String.valueOf(maxValue));
                customFieldValueService.createOrUpdateCustomFieldValue(dto);
            }
        }
    }

    private void evaluateMin(Rule rule) {
        if ("Facility".equalsIgnoreCase(rule.getTargetEntity())) {
            List<Facility> facilities = facilityRepository.findAll();
            for (Facility facility : facilities) {
                List<CarbonFootprint> cfs = carbonFootprintRepository.findAll().stream()
                        .filter(cf -> cf.getFacilityId().equals(facility.getId()))
                        .toList();
                OptionalDouble minOptional = cfs.stream()
                        .mapToDouble(cf -> {
                            if ("co2".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getCo2() != null ? cf.getCo2() : 0.0;
                            } else if ("input_amount".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getInputAmount() != null ? cf.getInputAmount() : 0.0;
                            }
                            return 0.0;
                        })
                        .min();

                double minValue = minOptional.orElse(0.0);

                CustomFieldValueDto dto = new CustomFieldValueDto();
                dto.setEntityType(rule.getTargetEntity());
                dto.setEntityId(facility.getId());
                dto.setFieldName(rule.getTargetField());
                dto.setFieldValue(String.valueOf(minValue));
                customFieldValueService.createOrUpdateCustomFieldValue(dto);
            }
        }
    }

    private void evaluateEquals(Rule rule) {
        if ("Facility".equalsIgnoreCase(rule.getTargetEntity())) {
            List<Facility> facilities = facilityRepository.findAll();

            for (Facility facility : facilities) {
                List<CarbonFootprint> cfs = carbonFootprintRepository.findAll().stream()
                        .filter(cf -> cf.getFacilityId().equals(facility.getId()))
                        .toList();
                long distinctCount = cfs.stream()
                        .map(cf -> {
                            if ("co2".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getCo2() != null ? cf.getCo2() : 0.0;
                            } else if ("input_amount".equalsIgnoreCase(rule.getSourceField())) {
                                return cf.getInputAmount() != null ? cf.getInputAmount() : 0.0;
                            }
                            return 0.0;
                        })
                        .distinct()
                        .count();
                boolean allEqual = !cfs.isEmpty() && distinctCount == 1;
                CustomFieldValueDto dto = new CustomFieldValueDto();
                dto.setEntityType(rule.getTargetEntity());
                dto.setEntityId(facility.getId());
                dto.setFieldName(rule.getTargetField());      // Ej. "all_values_equal"
                dto.setFieldValue(String.valueOf(allEqual));
                customFieldValueService.createOrUpdateCustomFieldValue(dto);
            }
        }
    }

}
