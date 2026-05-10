package com.tracker.habit_tracker.mapper;

import com.tracker.habit_tracker.dto.RecordResponse;
import com.tracker.habit_tracker.entity.HabitRecord;
import org.springframework.stereotype.Component;

@Component
public class RecordMaper {

    public RecordResponse toResponse(HabitRecord record) {

        RecordResponse response = new RecordResponse();

        response.setId(record.getId());
        response.setHabitId(record.getHabitId());
        response.setCreatedAt(record.getCreatedAt());

        return response;
    }
}
