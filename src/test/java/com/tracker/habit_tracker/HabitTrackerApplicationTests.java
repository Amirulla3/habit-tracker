package com.tracker.habit_tracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsServiceTest {

    @Test
    void currentStreakShouldBeCalculatedCorrectly() {

        List<LocalDate> dates = List.of(
                LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1),
                LocalDate.now()
        );

        int currentStreak = 0;

        LocalDate currentDate =
                LocalDate.now();

        while (dates.contains(currentDate)) {

            currentStreak++;

            currentDate =
                    currentDate.minusDays(1);
        }

        assertEquals(
                3,
                currentStreak
        );
    }

    @Test
    void currentStreakShouldBreakIfDayMissed() {

        List<LocalDate> dates = List.of(
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                LocalDate.now()
        );

        int currentStreak = 0;

        LocalDate currentDate =
                LocalDate.now();

        while (dates.contains(currentDate)) {

            currentStreak++;

            currentDate =
                    currentDate.minusDays(1);
        }

        assertEquals(
                2,
                currentStreak
        );
    }

    @Test
    void bestStreakShouldFindMaximumSeries() {

        List<LocalDate> dates = List.of(
                LocalDate.now().minusDays(6),
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(4),
                LocalDate.now().minusDays(1)
        );

        int bestStreak = 1;

        int tempStreak = 1;

        for (int i = 1; i < dates.size(); i++) {

            LocalDate previousDate =
                    dates.get(i - 1);

            LocalDate currentDate =
                    dates.get(i);

            if (previousDate.plusDays(1)
                    .equals(currentDate)) {

                tempStreak++;

            } else {

                if (tempStreak > bestStreak) {
                    bestStreak = tempStreak;
                }

                tempStreak = 1;
            }
        }

        if (tempStreak > bestStreak) {
            bestStreak = tempStreak;
        }

        assertEquals(
                3,
                bestStreak
        );
    }

    @Test
    void completionRateShouldBeCalculatedCorrectly() {

        int completedDays = 5;

        long totalDays = 10;

        double completionRate =
                ((double) completedDays
                        / totalDays) * 100;

        assertEquals(
                50.0,
                completionRate
        );
    }
}