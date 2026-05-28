package com.tracker.habit_tracker.dto.dailyStatsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitStatsResponse {

    private int totalCompletions;

    private int currentStreak;

    private int bestStreak;

    private double completionRate;

}