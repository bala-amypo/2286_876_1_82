package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productivity")
public class ProductivityController {

    private final ProductivityService service;

    public ProductivityController(ProductivityService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<ProductivityMetricRecord> submit(
            @RequestBody ProductivityMetricRecord record) {
        return ResponseEntity.ok(service.submitMetrics(record));
    }
}
