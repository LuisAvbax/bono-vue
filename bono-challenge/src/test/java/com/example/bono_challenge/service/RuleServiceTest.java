package com.example.bono_challenge.service;

import com.example.bono_challenge.dto.RuleDto;
import com.example.bono_challenge.entity.Rule;
import com.example.bono_challenge.repository.RuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RuleServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private RuleService ruleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRule() {
        Rule mockRule = new Rule();
        mockRule.setId(1L);
        mockRule.setTargetEntity("Facility");
        mockRule.setTargetField("total_co2");

        RuleDto dto = new RuleDto();
        dto.setTargetEntity("Facility");
        dto.setTargetField("total_co2");

        when(ruleRepository.save(any(Rule.class))).thenReturn(mockRule);

        Rule result = ruleService.createRule(dto);

        assertEquals("Facility", result.getTargetEntity());
        assertEquals("total_co2", result.getTargetField());

        verify(ruleRepository, times(1)).save(any(Rule.class));
    }

    @Test
    void testGetRule() {
        Rule mockRule = new Rule();
        mockRule.setId(1L);
        mockRule.setTargetEntity("Facility");
        mockRule.setTargetField("total_co2");

        when(ruleRepository.findById(1L)).thenReturn(Optional.of(mockRule));

        Rule result = ruleService.getRule(1L);

        assertEquals("Facility", result.getTargetEntity());
        assertEquals("total_co2", result.getTargetField());

        verify(ruleRepository, times(1)).findById(1L);
    }

    @Test
    void testGetRuleNotFound() {
        when(ruleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ruleService.getRule(1L));

        verify(ruleRepository, times(1)).findById(1L);
    }
}
