package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
@Tag(name = "Anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService service;

    public AnomalyFlagController(AnomalyFlagService service) {
        this.service = service;
    }

    @PostMapping
    public AnomalyFlagRecord flag(@RequestBody AnomalyFlagRecord record) {
        return service.flagAnomaly(record);
    }

    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolve(@PathVariable Long id) {
        return service.resolveFlag(id);
    }

    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> byMetric(@PathVariable Long metricId) {
        return service.getFlagsByMetric(metricId);
    }

    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return service.getAllFlags();
    }
}
