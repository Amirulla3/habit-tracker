package com.tracker.habit_tracker.service;

import com.tracker.habit_tracker.dto.HabitRequest;
import com.tracker.habit_tracker.dto.HabitResponse;
import com.tracker.habit_tracker.entity.Habit;
import com.tracker.habit_tracker.exception.HabitNotFoundException;
import com.tracker.habit_tracker.mapper.HabitMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HabitService {

    private Long nextId = 1L;

    private final HashMap<Long, Habit> habits = new HashMap<>();

    private final HabitMapper habitMapper;

    public HabitService(HabitMapper habitMapper) {
        this.habitMapper = habitMapper;
    }

    public HabitResponse create(HabitRequest request) {

        Habit habit = habitMapper.toEntity(request);

        habit.setId(nextId);
        habit.setCreatedAt(LocalDateTime.now());

        habits.put(nextId, habit);

        nextId++;

        return habitMapper.toResponse(habit);
    }

    public List<HabitResponse> getAll() {

        List<HabitResponse> responses = new ArrayList<>();

        for (Habit habit : habits.values()) {
            responses.add(habitMapper.toResponse(habit));
        }
        return responses;
    }

    public HabitResponse getById(Long id) {
        Habit habit = habits.get(id);
        if (habit == null) {
            throw new HabitNotFoundException();
        }
        return habitMapper.toResponse(habit);
    }

    public HabitResponse update(Long id, HabitRequest habitRequest)
    {
        Habit updateHabit = habits.get(id);

        if (updateHabit == null) {
            throw new HabitNotFoundException();
        }
        updateHabit.setName(habitRequest.getName());
        updateHabit.setDescription(habitRequest.getDescription());
        updateHabit.setTarget(habitRequest.getTarget());

        habits.put(id, updateHabit);

        return habitMapper.toResponse(updateHabit);

        }

    public void delete(Long id) {
        if (!habits.containsKey(id)) {
            throw new HabitNotFoundException();
        }
        habits.remove(id);
    }
}

