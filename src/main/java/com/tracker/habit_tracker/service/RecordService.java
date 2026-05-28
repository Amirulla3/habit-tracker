package com.tracker.habit_tracker.service;
import com.tracker.habit_tracker.dto.dailyStatsDTO.HabitStatsResponse;
import com.tracker.habit_tracker.dto.recordDTO.RecordResponse;
import com.tracker.habit_tracker.entity.Habit;
import com.tracker.habit_tracker.entity.HabitRecord;
import com.tracker.habit_tracker.mapper.RecordMapper;
import com.tracker.habit_tracker.repository.HabitRepository;
import com.tracker.habit_tracker.repository.RecordRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class RecordService {
    private Long nextId = 1L;
    private final HashMap<Long, List<HabitRecord>> records = new HashMap<>();
    private final RecordMapper recordMapper;
    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;

    public RecordService(RecordMapper recordMapper, HabitRepository habitRepository, RecordRepository recordRepository) {

        this.recordMapper = recordMapper;
        this.habitRepository = habitRepository;
        this.recordRepository = recordRepository;

    }
    public RecordResponse create(Long habitId){
        HabitRecord habitRecord = new HabitRecord();

        habitRecord.setId(nextId);
        habitRecord.setHabit(habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found")));
        habitRecord.setCreatedAt(LocalDateTime.now());

        records.putIfAbsent(habitId, new ArrayList<>());
        records.get(habitId).add(habitRecord);

        nextId++;

        return recordMapper.toResponse(habitRecord);
    }
    @Transactional(readOnly = true)
    public List<RecordResponse> getAllHabitId(Long habitId){
        List<HabitRecord> habitRecords = records.getOrDefault(habitId, new
                ArrayList<>());
        List<RecordResponse> responses = new ArrayList<>();
        for (HabitRecord record : habitRecords) {
            responses.add(recordMapper.toResponse(record));
        }
        return responses;
    }
    @Transactional
    public RecordResponse completeHabit(Long habitId){

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(()->new RuntimeException("Habit bot found"));

        boolean alreadyCompleted = recordRepository.existsByHabitIdAndCreatedAt(habitId, LocalDateTime.now());
        if (alreadyCompleted){
            throw new RuntimeException("Habit already completed today");
        }
        HabitRecord habitRecord = new HabitRecord();
        habitRecord.setHabit(habit);
        habitRecord.setCreatedAt(LocalDateTime.now());

        HabitRecord savedHabit = recordRepository.save(habitRecord);

        return recordMapper.toResponse(savedHabit);

        }
        public List<RecordResponse> getAllByHabitId(long habitId){
        return recordRepository.findByHabitId(habitId).stream()
                .map(recordMapper::toResponse)
                .toList();
        }
        @Transactional(readOnly = true)
        public HabitStatsResponse getHabitStats(Long habitId){
            LocalDate currentDate = LocalDate.now();
            int currentStreak = 0;
            int bestStreak = 0;
            int tempStreak = 0;

            HabitStatsResponse habitStatsResponse = new HabitStatsResponse();
            List<HabitRecord> records = recordRepository.findByHabitId(habitId);
            habitStatsResponse.setTotalCompletions(records.size());
            List<LocalDate> completionDates = records.stream()
                    .map(record -> record.getCreatedAt().toLocalDate()).sorted().toList();

            while (completionDates.contains(currentDate)){
                currentStreak += 1;
                currentDate = currentDate.minusDays(1);
            }
            habitStatsResponse.setCurrentStreak(currentStreak);

            for (int i = 1; i < completionDates.size(); i++){
                LocalDate previousDate = completionDates.get(i - 1);
                LocalDate currentCompletionDate = completionDates.get(i);

                if (previousDate.plusDays(1).equals(currentCompletionDate)){
                    tempStreak++;
                } else {
                    if (tempStreak > bestStreak){
                        bestStreak = tempStreak;
                    }
                    tempStreak = 1;
                }
            }
            if (tempStreak > bestStreak){
                bestStreak = tempStreak;
            }
            habitStatsResponse.setBestStreak(bestStreak);

            Habit habit = habitRepository.findById(habitId)
                    .orElseThrow(() -> new RuntimeException("Habit not found!"));

            LocalDateTime habitCreatedDate = habit.getCreatedAt();

            long totalDays = ChronoUnit.DAYS.between(habitCreatedDate, LocalDate.now());

            int completedDays = records.size();

            if(totalDays == 0){
                totalDays = 1;
            }
            double completionRate = ((double) completedDays/totalDays) * 100;

            habitStatsResponse.setCompletionRate(completionRate);

            return habitStatsResponse;

        }
    }
