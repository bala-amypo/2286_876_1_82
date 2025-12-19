package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnomalyFlagDto {
    private Long employeeId;
    private String severity;
    private String details;
}
