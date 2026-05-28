package com.tracker.habit_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "target_days")
    private Integer targetDays;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HabitRecord> records = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getTargetDays() {
        return this.targetDays;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public List<HabitRecord> getRecords() {
        return this.records;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDays(Integer targetDays) {
        this.targetDays = targetDays;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRecords(List<HabitRecord> records) {
        this.records = records;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Habit other)) return false;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$targetDays = this.getTargetDays();
        final Object other$targetDays = other.getTargetDays();
        if (this$targetDays == null ? other$targetDays != null : !this$targetDays.equals(other$targetDays))
            return false;
        final Object this$createdAt = this.getCreatedAt();
        final Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final Object this$records = this.getRecords();
        final Object other$records = other.getRecords();
        if (this$records == null ? other$records != null : !this$records.equals(other$records)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Habit;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $targetDays = this.getTargetDays();
        result = result * PRIME + ($targetDays == null ? 43 : $targetDays.hashCode());
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final Object $records = this.getRecords();
        result = result * PRIME + ($records == null ? 43 : $records.hashCode());
        return result;
    }

    public String toString() {
        return "Habit(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", targetDays=" + this.getTargetDays() + ", createdAt=" + this.getCreatedAt() + ", records=" + this.getRecords() + ")";
    }
}