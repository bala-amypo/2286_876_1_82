package com.example.demo.service;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.TeamSummaryRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamSummaryService {

    private final TeamSummaryRecordRepository summaryRepo;
    private final ProductivityMetricRecordRepository metricRepo;

    public TeamSummaryService(
            TeamSummaryRecordRepository summaryRepo,
            ProductivityMetricRecordRepository metricRepo) {
        this.summaryRepo = summaryRepo;
        this.metricRepo = metricRepo;
    }

    public TeamSummaryRecord generateSummary(String teamName, LocalDate date) {
        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(date);
        summary.setAvgHoursLogged(0.0);
        summary.setAvgTasksCompleted(0.0);
        summary.setAvgScore(0.0);
        summary.setAnomalyCount(0);
        return summaryRepo.save(summary);
    }

    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return summaryRepo.findAll();
    }

    public List<TeamSummaryRecord> getAllSummaries() {
        return summaryRepo.findAll();
    }
}
