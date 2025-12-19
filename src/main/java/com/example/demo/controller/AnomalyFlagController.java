package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-flags")
public class AnomalyFlagController {

    private final AnomalyFlagService service;

    public AnomalyFlagController(AnomalyFlagService service) {
        this.service = service;
    }

    /**
     * Get all anomaly flags for a given metric
     */
    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> getFlagsByMetric(
            @PathVariable Long metricId
    ) {
        return service.getFlagsByMetric(metricId);
    }
}
