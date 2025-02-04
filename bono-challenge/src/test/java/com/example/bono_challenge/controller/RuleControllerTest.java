package com.example.bono_challenge.controller;

import com.example.bono_challenge.dto.RuleDto;
import com.example.bono_challenge.entity.Rule;
import com.example.bono_challenge.service.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RuleControllerTest {

    @Mock
    private RuleService ruleService;

    @InjectMocks
    private RuleController ruleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRule() {
        // Mock Rule entity
        Rule mockRule = new Rule();
        mockRule.setId(1L);
        mockRule.setTargetEntity("Facility");
        mockRule.setTargetField("total_co2");

        // Mock RuleDto
        RuleDto mockRuleDto = new RuleDto();
        mockRuleDto.setId(1L);
        mockRuleDto.setTargetEntity("Facility");
        mockRuleDto.setTargetField("total_co2");

        // Simulate service method behavior
        when(ruleService.createRule(any(RuleDto.class))).thenReturn(mockRule);
        when(ruleService.entityToDto(any(Rule.class))).thenReturn(mockRuleDto);

        // Call the controller
        RuleDto result = ruleController.createRule(mockRuleDto);

        // Assertions
        assertEquals(1L, result.getId());
        assertEquals("Facility", result.getTargetEntity());
        assertEquals("total_co2", result.getTargetField());

        // Verify the interactions
        verify(ruleService, times(1)).createRule(any(RuleDto.class));
        verify(ruleService, times(1)).entityToDto(any(Rule.class));
    }

    @Test
    void testUpdateRule() {
        // Mock Rule entity
        Rule mockRule = new Rule();
        mockRule.setId(1L);
        mockRule.setTargetEntity("Facility");
        mockRule.setTargetField("total_co2");

        // Mock RuleDto
        RuleDto mockRuleDto = new RuleDto();
        mockRuleDto.setId(1L);
        mockRuleDto.setTargetEntity("Facility");
        mockRuleDto.setTargetField("total_co2");

        // Simulate service method behavior
        when(ruleService.updateRule(eq(1L), any(RuleDto.class))).thenReturn(mockRule);
        when(ruleService.entityToDto(any(Rule.class))).thenReturn(mockRuleDto);

        // Call the controller
        RuleDto result = ruleController.updateRule(1L, mockRuleDto);

        // Assertions
        assertEquals(mockRuleDto.getId(), result.getId());
        assertEquals(mockRuleDto.getTargetEntity(), result.getTargetEntity());
        assertEquals(mockRuleDto.getTargetField(), result.getTargetField());

        // Verify the interactions
        verify(ruleService, times(1)).updateRule(eq(1L), any(RuleDto.class));
        verify(ruleService, times(1)).entityToDto(any(Rule.class));
    }

    @Test
    void testGetRule() {
        // Mock Rule entity
        Rule mockRule = new Rule();
        mockRule.setId(1L);
        mockRule.setTargetEntity("Facility");
        mockRule.setTargetField("total_co2");

        // Mock RuleDto
        RuleDto mockRuleDto = new RuleDto();
        mockRuleDto.setId(1L);
        mockRuleDto.setTargetEntity("Facility");
        mockRuleDto.setTargetField("total_co2");

        // Simulate service method behavior
        when(ruleService.getRule(1L)).thenReturn(mockRule);
        when(ruleService.entityToDto(any(Rule.class))).thenReturn(mockRuleDto);

        // Call the controller
        RuleDto result = ruleController.getRule(1L);

        // Assertions
        assertEquals(mockRuleDto.getId(), result.getId());
        assertEquals(mockRuleDto.getTargetEntity(), result.getTargetEntity());
        assertEquals(mockRuleDto.getTargetField(), result.getTargetField());

        // Verify the interactions
        verify(ruleService, times(1)).getRule(1L);
        verify(ruleService, times(1)).entityToDto(any(Rule.class));
    }

    @Test
    void testGetAllRules() {
        // Mock Rule entities
        Rule rule1 = new Rule();
        rule1.setId(1L);
        rule1.setTargetEntity("Facility");

        Rule rule2 = new Rule();
        rule2.setId(2L);
        rule2.setTargetEntity("Customer");

        // Mock RuleDto list
        RuleDto ruleDto1 = new RuleDto();
        ruleDto1.setId(1L);
        ruleDto1.setTargetEntity("Facility");

        RuleDto ruleDto2 = new RuleDto();
        ruleDto2.setId(2L);
        ruleDto2.setTargetEntity("Customer");

        // Simulate service method behavior
        when(ruleService.getAllRules()).thenReturn(Arrays.asList(rule1, rule2));
        when(ruleService.entityToDto(rule1)).thenReturn(ruleDto1);
        when(ruleService.entityToDto(rule2)).thenReturn(ruleDto2);

        // Call the controller
        List<RuleDto> result = ruleController.getAllRules();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Facility", result.get(0).getTargetEntity());
        assertEquals("Customer", result.get(1).getTargetEntity());

        // Verify the interactions
        verify(ruleService, times(1)).getAllRules();
        verify(ruleService, times(1)).entityToDto(rule1);
        verify(ruleService, times(1)).entityToDto(rule2);
    }
}
