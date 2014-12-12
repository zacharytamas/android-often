package com.zacharytamas.often.models.managers;

import android.content.Context;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.HabitOccurrence;
import com.zacharytamas.often.utils.Data;

import java.util.Date;

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

    /**
     *  Returns the Habits which are available for completion at the current moment.
     *  This excludes Habits whose next occurrence is in the future.
     */
    public RealmResults<Habit> getAvailableHabits() {
        return mRealm.where(Habit.class)
                     .lessThan("availableAt", new Date())
                     .findAll();
    }

}
