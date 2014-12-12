package com.zacharytamas.often.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class Habit extends RealmObject {

    private String title;
    private Date createdAt;
    private boolean required;
    private Date startAt;
    private byte repeatType;
    private byte repeatUnit;
    private int repeatScalar;
    private byte repeatWeekdays;
    private Date lastCompletedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
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

    public Date getLastCompletedAt() {
        return lastCompletedAt;
    }

    public void setLastCompletedAt(Date lastCompletedAt) {
        this.lastCompletedAt = lastCompletedAt;
    }
}
