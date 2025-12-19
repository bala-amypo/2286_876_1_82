package com.example.demo.model;

public class AnomalyRule {

    private Long id;
    private String ruleCode;
    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active;

    public AnomalyRule() {}

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public Double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(Double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
