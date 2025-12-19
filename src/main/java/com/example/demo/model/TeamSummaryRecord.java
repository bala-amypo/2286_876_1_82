package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TeamSummaryRecord {

    private Long id;
    private String teamName;
    private LocalDate summaryDate;
    private LocalDateTime generatedAt;

    public TeamSummaryRecord() {}

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setSummaryDate(LocalDate summaryDate) {
        this.summaryDate = summaryDate;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
