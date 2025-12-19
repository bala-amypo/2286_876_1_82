package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService service;

    public AnomalyRuleController(AnomalyRuleService service) {
        this.service = service;
    }

    // ✅ Create new anomaly rule
    @PostMapping
    public AnomalyRule createRule(@RequestBody AnomalyRule rule) {
        return service.createRule(rule);
    }

    // ✅ Update rule by ID
    @PutMapping("/{id}")
    public AnomalyRule updateRule(
            @PathVariable Long id,
            @RequestBody AnomalyRule rule) {
        return service.updateRule(id, rule);
    }

    // ✅ Get all ACTIVE rules
    @GetMapping("/active")
    public List<AnomalyRule> getActiveRules() {
        return service.getActiveRules();
    }

    // ✅ Get rule by METRIC NAME (IMPORTANT FIX)
    @GetMapping("/metric/{metricName}")
    public AnomalyRule getRuleByMetricName(@PathVariable String metricName) {
        return service.getRuleByMetricName(metricName);
    }

    // ✅ Get all rules
    @GetMapping
    public List<AnomalyRule> getAllRules() {
        return service.getAllRules();
    }
}
