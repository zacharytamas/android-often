package com.zacharytamas.often.utils;

import android.content.Context;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.HabitOccurrence;
import com.zacharytamas.often.models.Migration;
import com.zacharytamas.often.models.RepeatType;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by zacharytamas on 12/11/14.
 */
public class Data {

    public static Realm getRealm(Context context) {
        Realm realm;
        try {
            realm = Realm.getInstance(context);
        } catch (RealmMigrationNeededException e) {
            // If the Realm needs migration, migrate it.
            String defaultPath = (new File(context.getFilesDir(), Realm.DEFAULT_REALM_NAME)).getAbsolutePath();
            Realm.migrateRealmAtPath(defaultPath, new Migration());
            // Then get it again.
            realm = Realm.getInstance(context);
        }

        return realm;
    }

    public static void addTestData(Context context, boolean deleteFirst) {

        Realm realm = getRealm(context);
        Integer habitCount;

        if (deleteFirst) {
            realm.beginTransaction();
            realm.where(Habit.class).findAll().clear();
            realm.where(HabitOccurrence.class).findAll().clear();
            realm.commitTransaction();
            habitCount = 0;
        } else {
            RealmResults<Habit> habits = realm.where(Habit.class).findAll();
            habitCount = habits.size();
        }


        if (habitCount == 0) {

            realm.beginTransaction();

            Habit habit1 = realm.createObject(Habit.class);
            habit1.setTitle("Brush teeth before bed");
            habit1.setRepeatType(RepeatType.PERIODICAL);
            habit1.setLastCompletedAt(createDate(2014, 11, 10));
            habit1.setAvailableAt(createDate(2014, 5, 1));

            Habit habit2 = realm.createObject(Habit.class);
            habit2.setTitle("Wash face before bed");
            habit2.setRepeatType(RepeatType.PERIODICAL);
            habit2.setLastCompletedAt(createDate(2014, 11, 8));
            habit2.setAvailableAt(createDate(2014, 9, 1));

            Habit habit3 = realm.createObject(Habit.class);
            habit3.setTitle("Have car washed");
            habit3.setRepeatType(RepeatType.PERIODICAL);
            habit3.setAvailableAt(createDate(2014, 11, 25));

            realm.commitTransaction();

        }

    }

    private static Date createDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day, 0, 0, 0);
        return cal.getTime();
    }

}
