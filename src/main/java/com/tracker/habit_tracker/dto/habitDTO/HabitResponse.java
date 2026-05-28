package com.tracker.habit_tracker.dto.habitDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitResponse {

    private Long id;

    private String name;

    private String description;

    private Integer target;

    private LocalDateTime createdAt;
}