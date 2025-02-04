package com.example.bono_challenge.controller;

import com.example.bono_challenge.dto.RuleDto;
import com.example.bono_challenge.entity.Rule;
import com.example.bono_challenge.service.RuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Rule API", description = "APIs for managing rules and evaluating them for entities")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Operation(
            summary = "Create a Rule",
            description = "Creates a new rule to evaluate and compute custom field values for a target entity."
    )
    @PostMapping
    public RuleDto createRule(@RequestBody RuleDto dto) {
        Rule created = ruleService.createRule(dto);
        return ruleService.entityToDto(created);
    }

    @Operation(
            summary = "Update a Rule",
            description = "Updates an existing rule. This will also trigger re-evaluation of the rule."
    )
    @PutMapping("/{id}")
    public RuleDto updateRule(
            @Parameter(description = "The ID of the rule to update.", example = "1")
            @PathVariable Long id,
            @RequestBody RuleDto dto) {
        Rule updated = ruleService.updateRule(id, dto);
        return ruleService.entityToDto(updated);
    }

    @Operation(
            summary = "Get a Rule by ID",
            description = "Retrieves the details of a specific rule by its ID."
    )
    @GetMapping("/{id}")
    public RuleDto getRule(
            @Parameter(description = "The ID of the rule to retrieve.", example = "1")
            @PathVariable Long id) {
        Rule rule = ruleService.getRule(id);
        return ruleService.entityToDto(rule);
    }

    @Operation(
            summary = "List All Rules",
            description = "Retrieves all rules defined in the system."
    )
    @GetMapping
    public List<RuleDto> getAllRules() {
        return ruleService.getAllRules()
                .stream()
                .map(rule -> ruleService.entityToDto(rule))
                .collect(Collectors.toList());
    }
    @Operation(
            summary = "Delete a Rule",
            description = "Deletes an existing rule by its ID."
    )
    @DeleteMapping("/{id}")
    public void deleteRule(
            @Parameter(description = "The ID of the rule to delete.", example = "1")
            @PathVariable Long id) {
        ruleService.deleteRule(id);
    }

}
