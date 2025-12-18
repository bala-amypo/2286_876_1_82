package com.example.demo.service;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyRuleService {

    private final AnomalyRuleRepository repository;

    public AnomalyRuleService(AnomalyRuleRepository repository) {
        this.repository = repository;
    }

    public AnomalyRule createRule(AnomalyRule rule) {
        return repository.save(rule);
    }

    public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
        AnomalyRule rule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setDescription(updatedRule.getDescription());
        rule.setThresholdType(updatedRule.getThresholdType());
        rule.setThresholdValue(updatedRule.getThresholdValue());
        rule.setActive(updatedRule.getActive());

        return repository.save(rule);
    }

    public List<AnomalyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    public List<AnomalyRule> getAllRules() {
        return repository.findAll();
    }
}
