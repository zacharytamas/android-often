package com.zacharytamas.often.utils;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.RepeatType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zacharytamas on 12/12/14.
 */
public class Dates {

    public static Date nextAvailableAt(Habit habit, Date now) {

        Date nextAvailableAt = null;
        int calendarUnit = -1;

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(now);

        // This clunky thing is required (to my newbie Java knowledge) because if I
        // try to pass in anything not derived explicitly from the GregorianCalendar
        // constants, the compiler complains: even if the underlying values are
        // actually the same. This means even though I'm storing the repeatUnit as
        // GregorianCalendar constants, Java does not believe me.
        switch (habit.getRepeatUnit()) {
            case GregorianCalendar.HOUR:
                calendarUnit = GregorianCalendar.HOUR;
                break;
            case GregorianCalendar.DATE:
                calendarUnit = GregorianCalendar.DATE;
                break;
            case GregorianCalendar.MONTH:
                calendarUnit = GregorianCalendar.MONTH;
                break;
            case GregorianCalendar.YEAR:
                calendarUnit = GregorianCalendar.YEAR;
                break;
        }

        switch (habit.getRepeatType()) {
            case RepeatType.PERIODICAL:

                // If we couldn't figure out the unit, we can't do math, so just return now.
                if (calendarUnit == -1) {
                    return now;
                }

                calendar.add(calendarUnit, habit.getRepeatScalar());
                calendar.set(GregorianCalendar.HOUR, 0);
                calendar.set(GregorianCalendar.MINUTE, 0);
                calendar.set(GregorianCalendar.SECOND, 0);

                return calendar.getTime();
        }

        return nextAvailableAt;
    }

}
