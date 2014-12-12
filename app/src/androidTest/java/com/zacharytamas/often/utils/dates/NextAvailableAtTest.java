package com.zacharytamas.often.utils.dates;

import android.test.InstrumentationTestCase;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;
import com.zacharytamas.often.utils.Dates;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zacharytamas on 12/12/14.
 */
public class NextAvailableAtTest extends InstrumentationTestCase {

    Habit habit;
    Date now;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        habit = new Habit();
        habit.setTitle("Brush teeth");

        now = getDate(1992, 0, 31);
    }

    private void assertSameDay(Date d1, Date d2) {
        DateTime dateTime1 = new DateTime(d1);
        DateTime dateTime2 = new DateTime(d2);

        assertEquals(dateTime1.withTimeAtStartOfDay(),
                     dateTime2.withTimeAtStartOfDay());
    }

    private Date getDate(int year, int month, int date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(year, month, date, 0, 0, 0);
        return calendar.getTime();
    }

    public void test_repeatType_periodical() {

        habit.setRepeatType(RepeatType.PERIODICAL);
        habit.setRepeatScalar(2);

        // Case 1: repeatUnit is daily
        habit.setRepeatUnit(GregorianCalendar.DATE);
        assertSameDay(Dates.nextAvailableAt(habit, now), getDate(1992, 1, 2));

        // Case 2: repeatUnit is monthly
        habit.setRepeatUnit(GregorianCalendar.MONTH);
        assertSameDay(Dates.nextAvailableAt(habit, now), getDate(1992, 2, 31));

        // Case 3: repeatUnit is yearly
        habit.setRepeatUnit(GregorianCalendar.YEAR);
        assertSameDay(Dates.nextAvailableAt(habit, now), getDate(1994, 0, 31));

        // Case 4: repeatUnit is weekly
        habit.setRepeatUnit(RepeatUnit.WEEKLY);
        assertSameDay(Dates.nextAvailableAt(habit, now), getDate(1992, 1, 14));

    }

}
