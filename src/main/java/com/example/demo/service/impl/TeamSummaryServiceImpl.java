package com.example.demo.service.impl;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository repository;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {

        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(summaryDate);
        summary.setGeneratedAt(LocalDateTime.now());

        return repository.save(summary);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return repository.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return repository.findAll();
    }
}
