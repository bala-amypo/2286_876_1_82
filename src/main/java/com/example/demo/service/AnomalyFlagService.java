package com.example.demo.service;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyFlagService {

    private final AnomalyFlagRecordRepository repository;

    public AnomalyFlagService(AnomalyFlagRecordRepository repository) {
        this.repository = repository;
    }

    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord flag) {
        return repository.save(flag);
    }

    public AnomalyFlagRecord resolveFlag(Long id) {
        AnomalyFlagRecord flag = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flag not found"));

        flag.setResolved(true);
        return repository.save(flag);
    }

    public List<AnomalyFlagRecord> getFlagsByMetric(Long metricId) {
        return repository.findByMetricId(metricId);
    }

    public List<AnomalyFlagRecord> getAllFlags() {
        return repository.findAll();
    }
}
