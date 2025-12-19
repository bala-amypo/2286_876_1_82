package com.example.demo.repository;

import com.example.demo.model.AnomalyRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnomalyRuleRepository extends JpaRepository<AnomalyRule, Long> {

    Optional<AnomalyRule> findByMetricName(String metricName);
}
