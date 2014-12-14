package com.zacharytamas.often.utils.dates;

import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;
import com.zacharytamas.often.utils.Dates;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zacharytamas on 12/13/14.
 */
public class NextDueAtTest extends DatesTest {

    public void test_nextDueAt() {
        habit.setRepeatUnit(RepeatUnit.DAILY);
        habit.setRepeatScalar(1);
        habit.setRepeatType(RepeatType.PERIODICAL);

        Date nextAvailable = Dates.nextAvailableAt(habit, now);
        Date nextDue = Dates.nextDueAt(habit, now);

        assertSameDay(getDate(1992, 1, 1), nextAvailable);
        // Should be due at midnight of the day, which is 0:00:00 of the next day.
        assertSameDay(getDate(1992, 1, 2), nextDue);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(nextDue);

        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, calendar.get(Calendar.MINUTE));
        assertEquals(0, calendar.get(Calendar.SECOND));

    }

}
