package com.example.demo.dto;

public class AnomalyFlagDto {

    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public String getRuleCode() {
        return
