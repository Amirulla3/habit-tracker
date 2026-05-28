package com.tracker.habit_tracker.dto.dailyStatsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyHabitResponse {

    Long id;

    String name;

    boolean completed;


}
