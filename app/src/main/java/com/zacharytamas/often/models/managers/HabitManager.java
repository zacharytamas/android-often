package com.zacharytamas.often.models.managers;

import android.content.Context;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.utils.Data;
import com.zacharytamas.often.utils.Habits;

import org.joda.time.DateTime;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A class for managing queries made for Habit objects.
 * Reminiscent of Django's Managers.
 */
public class HabitManager {

    private Realm mRealm;

    public HabitManager(Context context) {
        mRealm = Data.getRealm(context);
    }

    public RealmResults<Habit> getDueHabits() {
        return mRealm.where(Habit.class)
                     .lessThan("availableAt", new DateTime().toDate())
                     .equalTo("required", true)
                     .findAll();
    }

    /**
     *  Returns the Habits which are available for completion at the current moment.
     *  This excludes Habits whose next occurrence is in the future.
     */
    public RealmResults<Habit> getAvailableHabits() {
        return mRealm.where(Habit.class)
                     .lessThan("availableAt", new DateTime().toDate())
                     .equalTo("required", false)
                     .findAll();
    }

    public void completeHabit(Habit habit) {
        mRealm.beginTransaction();
        Habits.completeHabit(habit);
        mRealm.commitTransaction();
    }
}
