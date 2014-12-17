package com.zacharytamas.often.utils;

import com.zacharytamas.often.models.Habit;

import org.joda.time.DateTime;

/**
 * Created by zacharytamas on 12/14/14.
 */
public class Habits {

    public static void completeHabit(Habit habit) {

        DateTime now = new DateTime();
        habit.setLastCompletedAt(now.toDate());
        habit.setAvailableAt(Dates.nextAvailableAt(habit, now.toDate()));
        habit.setDueAt(Dates.nextDueAt(habit, now.toDate()));

        // TODO Test this
        if (Dates.isOverdue(habit)) {
            habit.setStreakValue(0);
        } else {
            habit.setStreakValue(habit.getStreakValue() + 1);
        }

    }

}
