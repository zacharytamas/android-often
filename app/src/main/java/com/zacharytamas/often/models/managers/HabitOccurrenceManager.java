package com.zacharytamas.often.models.managers;

import android.content.Context;

import com.zacharytamas.often.models.HabitOccurrence;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class HabitOccurrenceManager {

    private Context mContext;
    private Realm mRealm;

    public HabitOccurrenceManager(Context context) {
        mContext = context;
        mRealm = Realm.getInstance(mContext);
    }

    public RealmResults<HabitOccurrence> getHabitsForToday() {
        RealmResults<HabitOccurrence> occurrences = mRealm.where(HabitOccurrence.class)
                .lessThan("availableAt", new Date())
                .findAll();

        return occurrences;
    }

}
