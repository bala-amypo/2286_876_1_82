package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "productivity_metric_records",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employeeId", "date"})
    }
)
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private LocalDate date;
    private Double hoursLogged;
    private Integer tasksCompleted;
    private Integer meetingsAttended;
    private Double productivityScore;

    @Lob
    private String rawDataJson;

    private LocalDateTime submittedAt;

    public ProductivityMetricRecord() {}

    @PrePersist
    protected void onSubmit() {
        this.submittedAt = LocalDateTime.now();
    }

    // getters and setters
}
