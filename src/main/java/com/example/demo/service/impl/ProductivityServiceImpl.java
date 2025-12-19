package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductivityServiceImpl implements ProductivityService {

    @Override
    public ProductivityMetricRecord submitMetrics(ProductivityMetricRecord metric) {

        double score =
                (metric.getHoursLogged() * 1.5) +
                (metric.getTasksCompleted() * 2.0) -
                (metric.getMeetingsAttended() * 0.5);

        metric.setProductivityScore(score);
        metric.setSubmittedAt(LocalDateTime.now());

        return metric;
    }
}
