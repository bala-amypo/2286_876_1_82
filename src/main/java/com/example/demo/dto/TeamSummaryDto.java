package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeamSummaryDto {
    private String teamName;
    private LocalDate summaryDate;
    private Double avgScore;
    private Integer anomalyCount;
}
