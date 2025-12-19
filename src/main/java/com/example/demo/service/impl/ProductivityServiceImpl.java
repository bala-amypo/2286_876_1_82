package com.example.demo.service.impl;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityService;
import com.example.demo.util.ProductivityCalculator;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductivityServiceImpl implements ProductivityService {

    private final ProductivityMetricRecordRepository metricRepository;
    private final EmployeeProfileRepository employeeRepository;

    // Constructor injection
    public ProductivityServiceImpl(ProductivityMetricRecordRepository metricRepository,
                                   EmployeeProfileRepository employeeRepository) {
        this.metricRepository = metricRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {

        // Validate employee exists
        EmployeeProfile employee = employeeRepository
                .findById(metric.getEmployeeId())
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        // Check duplicate (employee + date)
        metricRepository.findByEmployeeIdAndDate(
                metric.getEmployeeId(),
                metric.getDate()
        ).ifPresent(m -> {
            throw new IllegalStateException("Metric already exists");
        });

        // Compute score
        double score = ProductivityCalculator.computeScore(
                metric.getHoursLogged(),
                metric.getTasksCompleted(),
                metric.getMeetingsAttended()
        );

        metric.setProductivityScore(score);
        metric.setSubmittedAt(LocalDateTime.now());

        return metricRepository.save(metric);
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {

        ProductivityMetricRecord existing = metricRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Metric not found"));

        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());

        double score = ProductivityCalculator.computeScore(
                updated.getHoursLogged(),
                updated.getTasksCompleted(),
                updated.getMeetingsAttended()
        );

        existing.setProductivityScore(score);

        return metricRepository.save(existing);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepository.findByEmployeeId(employeeId);
    }

    @Override
    public ProductivityMetricRecord getMetricById(Long id) {
        return metricRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Metric not found"));
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }
}
