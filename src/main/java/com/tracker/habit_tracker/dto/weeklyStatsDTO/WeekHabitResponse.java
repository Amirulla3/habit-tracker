package com.tracker.habit_tracker.dto.weeklyStatsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekHabitResponse {
    LocalDate date;
    int completedCount;
    int totalCount;
}
