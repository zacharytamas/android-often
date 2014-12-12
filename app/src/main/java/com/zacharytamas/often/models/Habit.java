package com.zacharytamas.often.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * A Habit the user does at some frequency.
 */
public class Habit extends RealmObject {

    private String title;
    private boolean required;
    private byte repeatType;
    private byte repeatUnit;
    private int repeatScalar;
    private byte repeatWeekdays;

    private Date createdAt;
    private Date availableAt;
    private Date lastCompletedAt;
    private Date dueAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public byte getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(byte repeatType) {
        this.repeatType = repeatType;
    }

    public byte getRepeatUnit() {
        return repeatUnit;
    }

    public void setRepeatUnit(byte repeatUnit) {
        this.repeatUnit = repeatUnit;
    }

    public int getRepeatScalar() {
        return repeatScalar;
    }

    public void setRepeatScalar(int repeatScalar) {
        this.repeatScalar = repeatScalar;
    }

    public byte getRepeatWeekdays() {
        return repeatWeekdays;
    }

    public void setRepeatWeekdays(byte repeatWeekdays) {
        this.repeatWeekdays = repeatWeekdays;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(Date availableAt) {
        this.availableAt = availableAt;
    }

    public Date getLastCompletedAt() {
        return lastCompletedAt;
    }

    public void setLastCompletedAt(Date lastCompletedAt) {
        this.lastCompletedAt = lastCompletedAt;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

}
