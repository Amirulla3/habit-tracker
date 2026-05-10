package com.tracker.habit_tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habit {

    private Long id;

    private String name;

    private String description;

    private Integer target;

    private LocalDateTime createdAt;
}