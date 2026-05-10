package com.tracker.habit_tracker.controller;

import com.tracker.habit_tracker.dto.HabitRequest;
import com.tracker.habit_tracker.dto.HabitResponse;
import com.tracker.habit_tracker.dto.RecordResponse;
import com.tracker.habit_tracker.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService habitService;
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }
    @PostMapping
    public ResponseEntity<HabitResponse> create(@Valid @RequestBody HabitRequest request){
        HabitResponse created = habitService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAll(){
        return ResponseEntity.ok(habitService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitResponse> getbyId(@PathVariable Long id){
        return ResponseEntity.ok(habitService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<HabitResponse> update(@PathVariable Long id, @Valid @RequestBody HabitRequest request){
        return ResponseEntity.ok(habitService.update(id, request));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        habitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
