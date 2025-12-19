package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeProfileDto {
    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;
}
