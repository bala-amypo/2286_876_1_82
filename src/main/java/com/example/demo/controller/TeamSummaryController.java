package com.example.demo.controller;

import com.example.demo.dto.TeamSummaryDto;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService teamSummaryService;

    public TeamSummaryController(TeamSummaryService teamSummaryService) {
        this.teamSummaryService = teamSummaryService;
    }

    @PostMapping("/generate")
    public ResponseEntity<TeamSummaryDto> generateSummary(@RequestParam String teamName, 
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate summaryDate) {
        TeamSummaryRecord summary = teamSummaryService.generateSummary(teamName, summaryDate);
        return ResponseEntity.ok(toDto(summary));
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<TeamSummaryDto>> getSummariesByTeam(@PathVariable String teamName) {
        List<TeamSummaryRecord> summaries = teamSummaryService.getSummariesByTeam(teamName);
        List<TeamSummaryDto> dtos = summaries.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamSummaryDto> getSummary(@PathVariable Long id) {
        List<TeamSummaryRecord> allSummaries = teamSummaryService.getAllSummaries();
        return allSummaries.stream()
                .filter(summary -> summary.getId().equals(id))
                .findFirst()
                .map(summary -> ResponseEntity.ok(toDto(summary)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryDto>> getAllSummaries() {
        List<TeamSummaryRecord> summaries = teamSummaryService.getAllSummaries();
        List<TeamSummaryDto> dtos = summaries.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private TeamSummaryDto toDto(TeamSummaryRecord summary) {
        return new TeamSummaryDto(summary.getId(), summary.getTeamName(), summary.getSummaryDate(),
                summary.getAvgHoursLogged(), summary.getAvgTasksCompleted(), summary.getAvgScore(),
                summary.getAnomalyCount(), summary.getGeneratedAt());
    }
}