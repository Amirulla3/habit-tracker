package com.tracker.habit_tracker.controller;

import com.tracker.habit_tracker.dto.recordDTO.RecordResponse;
import com.tracker.habit_tracker.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/habits/{id}/records")
public class RecordController {
    private final RecordService recordService;
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<RecordResponse> completeHabit(@PathVariable Long id) {
        RecordResponse response = recordService.completeHabit(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<RecordResponse>> getAllByHabitId(@PathVariable Long id){
        return ResponseEntity.ok(recordService.getAllByHabitId(id));
    }
//    @GetMapping("/daily")
//    public ResponseEntity<RecordResponse> get

}
