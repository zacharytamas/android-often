package com.zacharytamas.often.utils;

import android.content.Context;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.HabitOccurrence;
import com.zacharytamas.often.models.Migration;
import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
            habit1.setRepeatUnit(GregorianCalendar.DATE);
            habit1.setRepeatScalar(1);
            habit1.setLastCompletedAt(new DateTime().minusDays(1).toDate());
            habit1.setAvailableAt(createDate(2014, 5, 1));
            habit1.setDueAt(new DateTime().plusDays(1).toDate());
            habit1.setRequired(true);
            habit1.setStreakValue(20);

            Habit habit2 = realm.createObject(Habit.class);
            habit2.setTitle("Wash face before bed");
            habit2.setRepeatType(RepeatType.PERIODICAL);
            habit2.setLastCompletedAt(createDate(2014, 11, 8));
            habit2.setAvailableAt(createDate(2014, 9, 1));

            Habit habit3 = realm.createObject(Habit.class);
            habit3.setTitle("Have car washed");
            habit3.setRepeatType(RepeatType.PERIODICAL);
            habit3.setAvailableAt(createDate(2014, 10, 25));
            habit3.setLastCompletedAt(createDate(2014, 11, 1));

            Habit habit4 = realm.createObject(Habit.class);
            habit4.setTitle("Record weight");
            habit4.setRepeatType(RepeatType.PERIODICAL);
            habit4.setRepeatScalar(1);
            habit4.setRepeatUnit(RepeatUnit.DAILY);
            habit4.setAvailableAt(createDate(2014, 9, 1));
            habit4.setLastCompletedAt(createDate(2014, 9, 1));
            habit4.setDueAt(createDate(2014, 9, 2));
            habit4.setRequired(true);

            Habit habit5 = realm.createObject(Habit.class);
            habit5.setTitle("Do laundry");
            habit5.setRepeatType(RepeatType.WEEKLY);
            habit5.setRepeatScalar(1);
            habit5.setRepeatUnit(RepeatUnit.WEEKLY);
            habit5.setAvailableAt(createDate(2014, 9, 1));
            habit5.setLastCompletedAt(createDate(2014, 11, 1));
            habit5.setDueAt(createDate(2014, 9, 2));
            habit5.setRequired(true);

            Habit habit6 = realm.createObject(Habit.class);
            habit6.setTitle("Take out trash");
            habit6.setRepeatType(RepeatType.WEEKLY);
            habit6.setRepeatScalar(1);
            habit6.setRepeatUnit(RepeatUnit.WEEKLY);
            habit6.setRepeatOnWeekday(DateTimeConstants.THURSDAY, true);
            habit6.setAvailableAt(createDate(2014, 9, 1));
            habit6.setLastCompletedAt(createDate(2014, 9, 1));
            habit6.setDueAt(createDate(2014, 9, 2));
            habit6.setRequired(true);

            realm.commitTransaction();

        }

    }

    private static Date createDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day, 17, 0, 0);
        return cal.getTime();
    }

}
