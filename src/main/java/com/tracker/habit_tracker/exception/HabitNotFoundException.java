package com.tracker.habit_tracker.exception;

public class HabitNotFoundException extends RuntimeException{
    public HabitNotFoundException(){
        super("habit not found!");
    }
}
