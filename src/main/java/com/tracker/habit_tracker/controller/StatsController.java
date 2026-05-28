package com.tracker.habit_tracker.controller;


import com.tracker.habit_tracker.dto.dailyStatsDTO.DailyStatsResponse;
import com.tracker.habit_tracker.dto.weeklyStatsDTO.WeeklyStatsResponse;
import com.tracker.habit_tracker.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/stats")
public class StatsController {
    private StatsService statsService;

    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    @GetMapping("/daily")
    public ResponseEntity<DailyStatsResponse> getDailyStats(){
        DailyStatsResponse dailyStatsResponse = statsService.getDailyStats();
        return ResponseEntity.ok(dailyStatsResponse);
    }
    @GetMapping("/weekly")
    public ResponseEntity<WeeklyStatsResponse> getWeeklyStats(){
        WeeklyStatsResponse weeklyStatsResponse = statsService.getWeeklyStats();
        return ResponseEntity.ok(weeklyStatsResponse);
    }


}
