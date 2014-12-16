package com.zacharytamas.often.utils.dates;

import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;

import org.joda.time.DateTimeConstants;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.zacharytamas.often.utils.Dates.nextAvailableAt;

/**
 * Created by zacharytamas on 12/12/14.
 */
public class NextAvailableAtTest extends DatesTest {

    public void test_repeatType_periodically() {

        habit.setRepeatType(RepeatType.PERIODICAL);
        habit.setRepeatScalar(2);

        // Case 1: repeatUnit is daily
        habit.setRepeatUnit(GregorianCalendar.DATE);
        assertSameDay(nextAvailableAt(habit, now), getDate(1992, 2, 2));

        // Case 2: repeatUnit is monthly
        habit.setRepeatUnit(GregorianCalendar.MONTH);
        assertSameDay(nextAvailableAt(habit, now), getDate(1992, 3, 31));

        // Case 3: repeatUnit is yearly
        habit.setRepeatUnit(GregorianCalendar.YEAR);
        assertSameDay(nextAvailableAt(habit, now), getDate(1994, 1, 31));

        // Case 4: repeatUnit is weekly
        habit.setRepeatUnit(RepeatUnit.WEEKLY);
        assertSameDay(nextAvailableAt(habit, now), getDate(1992, 2, 14));

    }

    public void test_repeatType_weekly() {
        habit.setRepeatType(RepeatType.WEEKLY);
        habit.setRepeatScalar(1);
        habit.setRepeatOnWeekday(DateTimeConstants.MONDAY, true);
        habit.setRepeatOnWeekday(DateTimeConstants.SATURDAY, true);
        habit.setRepeatOnWeekday(DateTimeConstants.SUNDAY, true);

        // Next should be Saturday
        Date next = nextAvailableAt(habit, now);
        assertSameDay(getDate(1992, 2, 1), next);

        // After that is Sunday, the next day.
        Date next2 = nextAvailableAt(habit, next);
        assertSameDay(getDate(1992, 2, 2), next2);

        // After that is Monday, the next day.
        Date next3 = nextAvailableAt(habit, next2);
        assertSameDay(getDate(1992, 2, 3), next3);

        // After that should be the Saturday.
        Date next4 = nextAvailableAt(habit, next3);
        assertSameDay(getDate(1992, 2, 8), next4);

        // Set the repeatScalar to 2 weeks, which should make it skip a week.
        habit.setRepeatScalar(2);
        next3 = nextAvailableAt(habit, next);

        // Because the scalar is two weeks, it should skip to the Sunday of
        // the following week instead of the very next one.
        assertSameDay(getDate(1992, 2, 9), next3);

    }

}
