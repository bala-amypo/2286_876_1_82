package com.example.demo.controller;

import com.example.demo.dto.ProductivityMetricDto;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService productivityMetricService;

    public ProductivityMetricController(ProductivityMetricService productivityMetricService) {
        this.productivityMetricService = productivityMetricService;
    }

    @PostMapping
    public ResponseEntity<ProductivityMetricDto> recordMetric(@RequestBody ProductivityMetricDto dto) {
        ProductivityMetricRecord metric = new ProductivityMetricRecord(dto.getEmployeeId(), dto.getDate(),
                dto.getHoursLogged(), dto.getTasksCompleted(), dto.getMeetingsAttended());
        metric.setRawDataJson(dto.getRawDataJson());
        
        ProductivityMetricRecord created = productivityMetricService.recordMetric(metric);
        return ResponseEntity.ok(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductivityMetricDto> updateMetric(@PathVariable Long id, @RequestBody ProductivityMetricDto dto) {
        ProductivityMetricRecord updated = new ProductivityMetricRecord();
        updated.setHoursLogged(dto.getHoursLogged());
        updated.setTasksCompleted(dto.getTasksCompleted());
        updated.setMeetingsAttended(dto.getMeetingsAttended());
        updated.setRawDataJson(dto.getRawDataJson());
        
        ProductivityMetricRecord result = productivityMetricService.updateMetric(id, updated);
        return ResponseEntity.ok(toDto(result));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProductivityMetricDto>> getMetricsByEmployee(@PathVariable Long employeeId) {
        List<ProductivityMetricRecord> metrics = productivityMetricService.getMetricsByEmployee(employeeId);
        List<ProductivityMetricDto> dtos = metrics.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductivityMetricDto> getMetric(@PathVariable Long id) {
        Optional<ProductivityMetricRecord> metric = productivityMetricService.getMetricById(id);
        return metric.map(m -> ResponseEntity.ok(toDto(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductivityMetricDto>> getAllMetrics() {
        List<ProductivityMetricRecord> metrics = productivityMetricService.getAllMetrics();
        List<ProductivityMetricDto> dtos = metrics.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private ProductivityMetricDto toDto(ProductivityMetricRecord metric) {
        return new ProductivityMetricDto(metric.getId(), metric.getEmployeeId(), metric.getDate(),
                metric.getHoursLogged(), metric.getTasksCompleted(), metric.getMeetingsAttended(),
                metric.getProductivityScore(), metric.getRawDataJson(), metric.getSubmittedAt());
    }
}