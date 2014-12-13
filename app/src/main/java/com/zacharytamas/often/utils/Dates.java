package com.zacharytamas.often.utils;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zacharytamas on 12/12/14.
 */
public class Dates {

    public static byte setBitForWeekday(byte mask, int weekday, boolean repeat) {

        byte weekdayBit = (byte) (weekday - 1);

        if (repeat) {  // set the bit
            return (byte) (mask | (1 << weekdayBit));
        } else {  // clear the bit
            return (byte) (mask & ~(1 << weekdayBit));
        }
    }

    public static boolean getBitForWeekday(byte mask, int weekday) {
        return (mask & (1 << (weekday - 1))) > 0;
    }

    public static Date nextAvailableAt(Habit habit, Date now) {

        Date nextAvailableAt = null;
        int calendarUnit = -1;
        int repeatScalar = habit.getRepeatScalar();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(now);

        // This clunky thing is required (to my newbie Java knowledge) because if I
        // try to pass in anything not derived explicitly from the GregorianCalendar
        // constants, the compiler complains: even if the underlying values are
        // actually the same. This means even though I'm storing the repeatUnit as
        // GregorianCalendar constants, Java does not believe me.
        switch (habit.getRepeatUnit()) {
            case GregorianCalendar.DATE:
                calendarUnit = GregorianCalendar.DATE;
                break;
            case GregorianCalendar.MONTH:
                calendarUnit = GregorianCalendar.MONTH;
                break;
            case GregorianCalendar.YEAR:
                calendarUnit = GregorianCalendar.YEAR;
                break;
            case RepeatUnit.WEEKLY:
                calendarUnit = GregorianCalendar.DATE;
                repeatScalar *= RepeatUnit.DAYS_IN_WEEK;
                break;
        }

        switch (habit.getRepeatType()) {
            case RepeatType.PERIODICAL:

                // If we couldn't figure out the unit, we can't do math, so just return now.
                if (calendarUnit == -1) {
                    return now;
                }

                calendar.add(calendarUnit, repeatScalar);
                calendar.set(GregorianCalendar.HOUR, 0);
                calendar.set(GregorianCalendar.MINUTE, 0);
                calendar.set(GregorianCalendar.SECOND, 0);

                return calendar.getTime();
            case RepeatType.WEEKLY:
                do {
                    calendar.add(GregorianCalendar.DATE, 1);

                    // If the repeatScalar is more than one and we've just entered
                    // a new week, move ahead.
                    if (repeatScalar > 1 &&
                        calendar.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) {
                        calendar.add(GregorianCalendar.DATE, (repeatScalar - 1) * 7);
                    }

                } while (!habit.getRepeatsOnWeekday(calendar.get(GregorianCalendar.DAY_OF_WEEK)));

                return calendar.getTime();
        }

        return nextAvailableAt;
    }

}
