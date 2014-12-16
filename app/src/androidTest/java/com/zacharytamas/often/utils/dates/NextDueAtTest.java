package com.zacharytamas.often.utils.dates;

import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;
import com.zacharytamas.often.utils.Dates;

import org.joda.time.DateTime;

import java.util.Date;

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

        assertSameDay(getDate(1992, 2, 1), nextAvailable);
        // Should be due at midnight of the day, which is 0:00:00 of the next day.
        assertSameDay(getDate(1992, 2, 2), nextDue);

        DateTime dt = new DateTime(nextDue);

        assertEquals(0, dt.getHourOfDay());
        assertEquals(0, dt.getMinuteOfHour());
        assertEquals(0, dt.getSecondOfMinute());

    }

}
