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

import static com.zacharytamas.often.utils.Dates.nextAvailableAt;

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

    public void test_repeatType_periodically() {

        habit.setRepeatType(RepeatType.PERIODICAL);
        habit.setRepeatScalar(2);

        // Case 1: repeatUnit is daily
        habit.setRepeatUnit(GregorianCalendar.DATE);
        assertSameDay(nextAvailableAt(habit, now), getDate(1992, 1, 2));

        // Case 2: repeatUnit is monthly
        habit.setRepeatUnit(GregorianCalendar.MONTH);
        assertSameDay(nextAvailableAt(habit, now), getDate(1992, 2, 31));

        // Case 3: repeatUnit is yearly
        habit.setRepeatUnit(GregorianCalendar.YEAR);
        assertSameDay(nextAvailableAt(habit, now), getDate(1994, 0, 31));

        // Case 4: repeatUnit is weekly
        habit.setRepeatUnit(RepeatUnit.WEEKLY);
        assertSameDay(nextAvailableAt(habit, now), getDate(1992, 1, 14));

    }

    public void test_repeatType_weekly() {
        habit.setRepeatType(RepeatType.WEEKLY);
        habit.setRepeatScalar(1);
        habit.setRepeatOnWeekday(GregorianCalendar.MONDAY, true);
        habit.setRepeatOnWeekday(GregorianCalendar.WEDNESDAY, true);

        // Next should be Monday
        Date next = nextAvailableAt(habit, now);
        assertSameDay(getDate(1992, 1, 3), next);

        // After that is Wednesday
        Date next2 = nextAvailableAt(habit, next);
        assertSameDay(getDate(1992, 1, 5), next2);

        // After that should be the following Monday.
        Date next3 = nextAvailableAt(habit, next2);
        assertSameDay(getDate(1992, 1, 10), next3);

        // Set the repeatScalar to 2 weeks, which should make it skip a week.
        habit.setRepeatScalar(2);
        next3 = nextAvailableAt(habit, next2);

        // Because the scalar is two weeks, it should skip to the Monday of
        // the following week instead of the very next one.
        assertSameDay(getDate(1992, 1, 17), next3);

    }

}
