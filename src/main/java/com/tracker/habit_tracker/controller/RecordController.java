package com.tracker.habit_tracker.controller;

import com.tracker.habit_tracker.dto.RecordResponse;
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
    public ResponseEntity<RecordResponse> create(
            @PathVariable Long id
    ) {
        RecordResponse response = recordService.create(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<RecordResponse>> getAl(@PathVariable Long id){
        return ResponseEntity.ok(recordService.getAllHabitId(id));
    }
}
