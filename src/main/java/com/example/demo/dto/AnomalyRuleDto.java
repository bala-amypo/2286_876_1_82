package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnomalyRuleDto {
    private String ruleCode;
    private String thresholdType;
    private Double thresholdValue;
}
