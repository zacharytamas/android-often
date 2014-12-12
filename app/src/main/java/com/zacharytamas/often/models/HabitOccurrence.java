package com.zacharytamas.often.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class HabitOccurrence extends RealmObject {

    private Habit habit;
    private Date completedAt;
    private Date dueAt;
    private Date availableAt;

    //

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

    public Date getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(Date availableAt) {
        this.availableAt = availableAt;
    }
}
