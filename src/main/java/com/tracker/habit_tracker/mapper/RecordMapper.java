package com.tracker.habit_tracker.mapper;

import com.tracker.habit_tracker.dto.recordDTO.RecordResponse;
import com.tracker.habit_tracker.entity.HabitRecord;
import org.springframework.stereotype.Component;
@Component
public class RecordMapper {
        public RecordResponse toResponse(HabitRecord record) {

            RecordResponse response = new RecordResponse();

            response.setId(record.getId());
            response.setHabitId(record.getId());
            response.setCreatedAt(record.getCreatedAt());

            return response;
        }
    }

