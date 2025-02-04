package com.example.bono_challenge.service;

import com.example.bono_challenge.dto.FilterDto;
import com.example.bono_challenge.dto.RuleDto;
import com.example.bono_challenge.dto.SourceDto;
import com.example.bono_challenge.entity.Rule;
import com.example.bono_challenge.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.List;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleEvaluationService ruleEvaluationService;

    @Autowired
    private ObjectMapper objectMapper;

    public Rule createRule(RuleDto dto) {
        Rule rule = dtoToEntity(dto);
        Rule saved = ruleRepository.save(rule);
        ruleEvaluationService.evaluateRuleAsync(saved);
        return saved;
    }

    public Rule updateRule(Long id, RuleDto dto) {
        Rule existing = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule no encontrada"));

        existing.setTargetEntity(dto.getTargetEntity());
        existing.setTargetField(dto.getTargetField());
        existing.setOperation(dto.getOperation());
        if (dto.getSource() != null) {
            existing.setSourceEntity(dto.getSource().getEntity());
            existing.setSourceField(dto.getSource().getField());
        } else {
            existing.setSourceEntity(null);
            existing.setSourceField(null);
        }
        if (dto.getFilter() != null) {
            existing.setFilterJson(serializeFilter(dto.getFilter()));
        } else {
            existing.setFilterJson(null);
        }

        Rule saved = ruleRepository.save(existing);
        ruleEvaluationService.evaluateRuleAsync(saved);
        return saved;
    }

    public Rule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule no encontrada"));
    }
    public void deleteRule(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new RuntimeException("Rule with ID " + id + " not found.");
        }
        ruleRepository.deleteById(id);
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
    private Rule dtoToEntity(RuleDto dto) {
        Rule rule = new Rule();
        rule.setTargetEntity(dto.getTargetEntity());
        rule.setTargetField(dto.getTargetField());
        rule.setOperation(dto.getOperation());
        if (dto.getSource() != null) {
            rule.setSourceEntity(dto.getSource().getEntity());
            rule.setSourceField(dto.getSource().getField());
        }
        if (dto.getFilter() != null) {
            rule.setFilterJson(serializeFilter(dto.getFilter()));
        }
        return rule;
    }
    public RuleDto entityToDto(Rule rule) {
        RuleDto dto = new RuleDto();
        dto.setId(rule.getId());
        dto.setTargetEntity(rule.getTargetEntity());
        dto.setTargetField(rule.getTargetField());
        dto.setOperation(rule.getOperation());
        if (rule.getSourceEntity() != null || rule.getSourceField() != null) {
            SourceDto source = new SourceDto();
            source.setEntity(rule.getSourceEntity());
            source.setField(rule.getSourceField());
            dto.setSource(source);
        }

        if (rule.getFilterJson() != null) {
            dto.setFilter(deserializeFilter(rule.getFilterJson()));
        }

        return dto;
    }
    private String serializeFilter(FilterDto filterDto) {
        try {
            return objectMapper.writeValueAsString(filterDto);
        } catch (Exception e) {
            throw new RuntimeException("Error serializando filter", e);
        }
    }

    private FilterDto deserializeFilter(String json) {
        try {
            return objectMapper.readValue(json, FilterDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializando filter", e);
        }
    }
}
