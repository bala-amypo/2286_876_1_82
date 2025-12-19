package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityService {

    ProductivityMetricRecord submitMetrics(ProductivityMetricRecord metric);
}
