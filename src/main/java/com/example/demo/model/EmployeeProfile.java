package com.example.demo.model;

import java.time.LocalDateTime;

public class EmployeeProfile {

    private Long id;
    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;
    private boolean active;
    private LocalDateTime createdAt;

    public EmployeeProfile() {}

    public Long getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
