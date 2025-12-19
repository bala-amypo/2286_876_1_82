package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;
import java.util.List;

public interface ProductivityService {
    List<ProductivityMetricRecord> getAllMetrics();
    ProductivityMetricRecord saveMetric(ProductivityMetricRecord metric);
}
