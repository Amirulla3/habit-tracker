package com.tracker.habit_tracker.mapper;

import com.tracker.habit_tracker.dto.habitDTO.HabitRequest;
import com.tracker.habit_tracker.dto.habitDTO.HabitResponse;
import com.tracker.habit_tracker.entity.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

    public Habit toEntity(HabitRequest request) {

        Habit habit = new Habit();

        habit.setName(request.getName());
        habit.setDescription(request.getDescription());
        habit.setTargetDays(request.getTarget());

        return habit;
    }
    public HabitResponse toResponse(Habit habit) {

        HabitResponse response = new HabitResponse();

        response.setId(habit.getId());
        response.setName(habit.getName());
        response.setDescription(habit.getDescription());
        response.setTarget(habit.getTargetDays());
        response.setCreatedAt(habit.getCreatedAt());

        return response;
    }
}
