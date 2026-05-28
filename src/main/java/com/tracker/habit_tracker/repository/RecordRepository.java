package com.tracker.habit_tracker.repository;

import com.tracker.habit_tracker.entity.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<HabitRecord, Long> {
    List<HabitRecord> findByHabitId(Long habitId);
    boolean existsByHabitIdAndCreatedAt(Long habitId, LocalDateTime date);
    @Query("""
            SELECT r 
            FROM HabitRecord r 
            WHERE r.createdAt BETWEEN :start AND :end
            """)
    List<HabitRecord> findByCreatedAtBetween(@Param("start") LocalDateTime start,
                                             @Param("end") LocalDateTime end);
    @Query("""
            SELECT r
            FROM HabitRecord r
            WHERE r.createdAt BETWEEN :start AND :end
            """)
    List<HabitRecord> findRecordsBetween(@Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end);

}
