package com.tracker.habit_tracker.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitRecord {

    private Long id;

    private Long habitId;

    private LocalDateTime createdAt;
}