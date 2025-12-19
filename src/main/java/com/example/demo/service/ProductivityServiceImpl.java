package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductivityServiceImpl implements ProductivityService {

    private final ProductivityMetricRepository repository;

    public ProductivityServiceImpl(ProductivityMetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return repository.findAll();
    }

    @Override
    public ProductivityMetricRecord saveMetric(ProductivityMetricRecord metric) {
        return repository.save(metric);
    }
}
