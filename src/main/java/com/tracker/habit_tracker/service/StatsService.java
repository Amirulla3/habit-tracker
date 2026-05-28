package com.tracker.habit_tracker.service;


import com.tracker.habit_tracker.dto.dailyStatsDTO.DailyHabitResponse;
import com.tracker.habit_tracker.dto.dailyStatsDTO.DailyStatsResponse;
import com.tracker.habit_tracker.dto.weeklyStatsDTO.WeekHabitResponse;
import com.tracker.habit_tracker.dto.weeklyStatsDTO.WeeklyStatsResponse;
import com.tracker.habit_tracker.entity.Habit;
import com.tracker.habit_tracker.entity.HabitRecord;
import com.tracker.habit_tracker.repository.HabitRepository;
import com.tracker.habit_tracker.repository.RecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {
    private RecordRepository recordRepository;
    private HabitRepository habitRepository;


    public StatsService(RecordRepository recordRepository,
                        HabitRepository habitRepository){
        this.recordRepository = recordRepository;
        this.habitRepository = habitRepository;
    }

    @Transactional(readOnly = true)
    public DailyStatsResponse getDailyStats(){
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23, 59, 59);
        List<Habit> habits = habitRepository.findAll();
        List<HabitRecord> todayRecords = recordRepository.findByCreatedAtBetween(start, end);
        List<DailyHabitResponse> responses =
                habits.stream()
                        .map(habit -> {

                            boolean completed =
                                    todayRecords.stream()
                                            .anyMatch(record ->
                                                    record.getHabit()
                                                            .getId()
                                                            .equals(
                                                                    habit.getId()));
                            DailyHabitResponse response =
                                    new DailyHabitResponse();

                            response.setId(habit.getId());

                            response.setName(habit.getName());

                            response.setCompleted(completed);

                            return response;}).toList();
        DailyStatsResponse response = new DailyStatsResponse();

        response.setDate(today);

        response.setHabits(responses);

        return response;
    }
    public WeeklyStatsResponse getWeeklyStats(){
        LocalDate today = LocalDate.now();
        long totalCount = habitRepository.count();
        List<WeekHabitResponse> week = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            LocalDate date = today.minusDays(i);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(23, 59, 59);
            List<HabitRecord> records = recordRepository.findRecordsBetween(start, end);
            int completedCount = records.size();
            WeekHabitResponse weekHabitResponse = new WeekHabitResponse();
            weekHabitResponse.setDate(date);
            weekHabitResponse.setCompletedCount(completedCount);
            weekHabitResponse.setTotalCount((int) totalCount);
            week.add(weekHabitResponse);
        }
        WeeklyStatsResponse response = new WeeklyStatsResponse();
        response.setWeek(week);
        return response;

    }
}
