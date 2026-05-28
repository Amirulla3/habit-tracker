package com.tracker.habit_tracker.dto.recordDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {

    private Long id;

    private Long habitId;

    private LocalDateTime createdAt;
}