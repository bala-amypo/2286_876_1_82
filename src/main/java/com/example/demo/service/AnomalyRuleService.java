package com.example.demo.service;

import com.example.demo.model.AnomalyRule;
import java.util.List;

public interface AnomalyRuleService {

    AnomalyRule createRule(AnomalyRule rule);

    AnomalyRule updateRule(Long id, AnomalyRule updatedRule);

    List<AnomalyRule> getActiveRules();

    AnomalyRule getRuleByCode(String ruleCode);

    List<AnomalyRule> getAllRules();
}
