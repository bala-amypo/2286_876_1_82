package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "anomaly_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleCode")
    }
)
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active;

    public AnomalyRule() {}

    // getters and setters
}
