package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;

    @Lob
    private String details;

    private LocalDateTime flaggedAt;
    private Boolean resolved;

    public AnomalyFlagRecord() {}

    @PrePersist
    protected void onFlag() {
        this.flaggedAt = LocalDateTime.now();
        if (this.resolved == null) {
            this.resolved = false;
        }
    }

    // getters and setters
}
