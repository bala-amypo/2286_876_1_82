package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityService service;

    public ProductivityMetricController(ProductivityService service) {
        this.service = service;
    }

    @PostMapping
    public ProductivityMetricRecord submit(@RequestBody ProductivityMetricRecord metric) {
        return service.submitMetrics(metric);
    }
}
