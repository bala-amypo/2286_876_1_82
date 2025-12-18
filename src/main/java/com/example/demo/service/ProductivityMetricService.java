package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.ProductivityCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final AnomalyRuleRepository ruleRepo;
    private final AnomalyFlagRecordRepository flagRepo;

    // ⚠️ Constructor order MUST NOT change
    public ProductivityMetricService(
            ProductivityMetricRecordRepository metricRepo,
            EmployeeProfileRepository employeeRepo,
            AnomalyRuleRepository ruleRepo,
            AnomalyFlagRecordRepository flagRepo) {
        this.metricRepo = metricRepo;
        this.employeeRepo = employeeRepo;
        this.ruleRepo = ruleRepo;
        this.flagRepo = flagRepo;
    }

    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {

        if (metricRepo.existsByEmployeeIdAndDate(
                metric.getEmployeeId(), metric.getDate())) {
            throw new IllegalStateException("Metric already exists");
        }

        EmployeeProfile emp = employeeRepo.findById(metric.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!emp.getActive()) {
            throw new RuntimeException("Employee not found");
        }

        double score = ProductivityCalculator.computeScore(
                metric.getHoursLogged(),
                metric.getTasksCompleted(),
                metric.getMeetingsAttended()
        );

        metric.setProductivityScore(score);
        metric.setSubmittedAt(LocalDateTime.now());

        return metricRepo.save(metric);
    }

    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepo.findByEmployeeId(employeeId);
    }

    public ProductivityMetricRecord getMetricById(Long id) {
        return metricRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));
    }

    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepo.findAll();
    }
}
