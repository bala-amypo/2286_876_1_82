package com.example.demo.model;

public class AnomalyFlagRecord {

    private Long id;
    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;
    private boolean resolved;

    public AnomalyFlagRecord() {}

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
