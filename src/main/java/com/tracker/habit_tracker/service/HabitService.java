package com.tracker.habit_tracker.service;

import com.tracker.habit_tracker.dto.habitDTO.HabitRequest;
import com.tracker.habit_tracker.dto.habitDTO.HabitResponse;
import com.tracker.habit_tracker.entity.Habit;
import com.tracker.habit_tracker.exception.HabitNotFoundException;
import com.tracker.habit_tracker.mapper.HabitMapper;
import com.tracker.habit_tracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitService {

    private final HabitMapper habitMapper;
    private final HabitRepository habitRepository;

    public HabitService(HabitMapper habitMapper, HabitRepository habitRepository) {
        this.habitMapper = habitMapper;
        this.habitRepository = habitRepository;
    }

    public HabitResponse create(HabitRequest request) {
        Habit habit = habitMapper.toEntity(request);
        habit.setCreatedAt(LocalDateTime.now());
        Habit savedHabit = habitRepository.save(habit);
        return habitMapper.toResponse(savedHabit);
    }

    public List<HabitResponse> getAll() {

        return habitRepository.findAll()
                .stream()
                .map(habitMapper::toResponse)
                .toList();
    }

    public HabitResponse getById(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() ->
                        new HabitNotFoundException()
                );

        return habitMapper.toResponse(habit);
    }

    public HabitResponse update(Long id, HabitRequest habitRequest)
    {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() ->
                        new HabitNotFoundException()
                );

        habit.setName(habitRequest.getName());
        habit.setDescription(habitRequest.getDescription());
        habit.setTargetDays(habitRequest.getTarget());

        Habit updatedHabit = habitRepository.save(habit);

        return habitMapper.toResponse(updatedHabit);

        }

    public void delete(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new HabitNotFoundException();
        }
        habitRepository.deleteById(id);
    }
}

