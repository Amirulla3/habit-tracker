package com.tracker.habit_tracker.repository;

import com.tracker.habit_tracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}


