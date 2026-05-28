package com.tracker.habit_tracker.dto.dailyStatsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyStatsResponse {
    LocalDate date;
    List<DailyHabitResponse> habits;
}
