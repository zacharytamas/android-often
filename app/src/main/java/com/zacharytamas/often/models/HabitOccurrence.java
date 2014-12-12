package com.zacharytamas.often.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class HabitOccurrence extends RealmObject {

    private Habit habit;
    private String note;
    private int streakLength;

    private Date completedAt;
    private Date wasDueAt;

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStreakLength() {
        return streakLength;
    }

    public void setStreakLength(int streakLength) {
        this.streakLength = streakLength;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Date getWasDueAt() {
        return wasDueAt;
    }

    public void setWasDueAt(Date wasDueAt) {
        this.wasDueAt = wasDueAt;
    }
}
