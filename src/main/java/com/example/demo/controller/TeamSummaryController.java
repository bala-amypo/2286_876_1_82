package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
@Tag(name = "Team Summaries")
public class TeamSummaryController {

    private final TeamSummaryService service;

    public TeamSummaryController(TeamSummaryService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public TeamSummaryRecord generate(
            @RequestParam String teamName,
            @RequestParam String date) {
        return service.generateSummary(teamName, LocalDate.parse(date));
    }

    @GetMapping("/team/{teamName}")
    public List<TeamSummaryRecord> byTeam(@PathVariable String teamName) {
        return service.getSummariesByTeam(teamName);
    }

    @GetMapping
    public List<TeamSummaryRecord> getAll() {
        return service.getAllSummaries();
    }
}
