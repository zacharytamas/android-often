package com.zacharytamas.often.utils.dates;

import android.test.InstrumentationTestCase;

import com.zacharytamas.often.models.Habit;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by zacharytamas on 12/13/14.
 */
public class DatesTest extends InstrumentationTestCase {

    Habit habit;
    Date now;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        habit = new Habit();
        habit.setTitle("Brush teeth");

        now = getDate(1992, 1, 31);
    }

    protected void assertSameDay(Date d1, Date d2) {
        DateTime dateTime1 = new DateTime(d1);
        DateTime dateTime2 = new DateTime(d2);

        assertEquals(dateTime1.withTimeAtStartOfDay(),
                dateTime2.withTimeAtStartOfDay());
    }

    protected Date getDate(int year, int month, int date) {
        return new DateTime(year, month, date, 0, 0).toDate();
    }

}
