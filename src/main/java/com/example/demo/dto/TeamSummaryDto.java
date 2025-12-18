package com.example.demo.dto;

import java.time.LocalDate;

public class TeamSummaryDto {

    private String teamName;
    private LocalDate summaryDate;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDate getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(LocalDate summaryDate) {
        this.summaryDate = summaryDate;
    }
}
