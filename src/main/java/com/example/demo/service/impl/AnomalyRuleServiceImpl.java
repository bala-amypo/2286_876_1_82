package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repository;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return repository.save(rule);
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
        AnomalyRule rule = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));

        rule.setThresholdType(updatedRule.getThresholdType());
        rule.setThresholdValue(updatedRule.getThresholdValue());
        rule.setActive(updatedRule.getActive());

        return repository.save(rule);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    // ✅ OPTION 1 FIX: use metricName instead of ruleCode
    @Override
    public AnomalyRule getRuleByMetricName(String metricName) {
        return repository.findByMetricName(metricName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return repository.findAll();
    }
}
