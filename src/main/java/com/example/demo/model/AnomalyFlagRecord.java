package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;

    @Column(columnDefinition = "TEXT")
    private String details;

    private Boolean resolved = false;
    private LocalDateTime flaggedAt = LocalDateTime.now();
}
