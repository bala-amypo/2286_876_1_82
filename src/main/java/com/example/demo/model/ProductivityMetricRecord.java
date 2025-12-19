package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductivityMetricRecord {

    private Long id;
    private Long employeeId;
    private LocalDate date;

    private int hoursLogged;
    private int tasksCompleted;
    private int meetingsAttended;

    private double productivityScore;
    private LocalDateTime submittedAt;

    public ProductivityMetricRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHoursLogged() {
        return hoursLogged;
    }

    public void setHoursLogged(int hoursLogged) {
        this.hoursLogged = hoursLogged;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public int getMeetingsAttended() {
        return meetingsAttended;
    }

    public void setMeetingsAttended(int meetingsAttended) {
        this.meetingsAttended = meetingsAttended;
    }

    public double getProductivityScore() {
        return productivityScore;
    }

    public void setProductivityScore(double productivityScore) {
        this.productivityScore = productivityScore;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
