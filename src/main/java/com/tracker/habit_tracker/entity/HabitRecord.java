package com.tracker.habit_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "habit_records")
public class HabitRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof HabitRecord)) return false;
        final HabitRecord other = (HabitRecord) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$createdAt = this.getCreatedAt();
        final Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final Object this$habit = this.getHabit();
        final Object other$habit = other.getHabit();
        if (this$habit == null ? other$habit != null : !this$habit.equals(other$habit)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HabitRecord;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final Object $habit = this.getHabit();
        result = result * PRIME + ($habit == null ? 43 : $habit.hashCode());
        return result;
    }

    public String toString() {
        return "HabitRecord(id=" + this.getId() + ", createdAt=" + this.getCreatedAt() + ", habit=" + this.getHabit() + ")";
    }
}
