package com.tracker.habit_tracker.service;
import com.tracker.habit_tracker.dto.RecordResponse;
import com.tracker.habit_tracker.entity.HabitRecord;
import com.tracker.habit_tracker.mapper.RecordMaper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class RecordService {
    private Long nextId = 1L;
    private final HashMap<Long, List<HabitRecord>> records = new HashMap<>();
    private final RecordMaper recordMaper;

    public RecordService(RecordMaper recordMaper) {
        this.recordMaper = recordMaper;
    }
    public RecordResponse create(Long habitId){
        HabitRecord habitRecord = new HabitRecord();

        habitRecord.setId(nextId);
        habitRecord.setHabitId(habitId);
        habitRecord.setCreatedAt(LocalDateTime.now());

        records.putIfAbsent(habitId, new ArrayList<>());
        records.get(habitId).add(habitRecord);

        nextId++;

        return recordMaper.toResponse(habitRecord);
    }
    public List<RecordResponse> getAllHabitId(Long habitId){
        List<HabitRecord> habitRecords = records.getOrDefault(habitId, new
                ArrayList<>());
        List<RecordResponse> responses = new ArrayList<>();
        for (HabitRecord record : habitRecords) {
            responses.add(recordMaper.toResponse(record));
        }
        return responses;
    }
}
