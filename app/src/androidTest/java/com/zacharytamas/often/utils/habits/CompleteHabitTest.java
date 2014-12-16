package com.zacharytamas.often.utils.habits;

import com.zacharytamas.often.models.Habit;
import com.zacharytamas.often.models.RepeatType;
import com.zacharytamas.often.models.RepeatUnit;
import com.zacharytamas.often.utils.Habits;
import com.zacharytamas.often.utils.dates.DatesTest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.util.Date;

import static com.zacharytamas.often.utils.Dates.nextAvailableAt;
import static com.zacharytamas.often.utils.Dates.nextDueAt;

/**
 * Created by zacharytamas on 12/14/14.
 */
public class CompleteHabitTest extends DatesTest {

    Date now;
    Habit habit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        now = getDate(1992, 1, 31);
        DateTimeUtils.setCurrentMillisFixed(new DateTime(now).getMillis());

        habit = new Habit();
        habit.setAvailableAt(now);
        habit.setDueAt(getDate(1992, 1, 1));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        DateTimeUtils.setCurrentMillisSystem();
    }

    public void test_completeHabit() {

        habit.setRepeatType(RepeatType.PERIODICAL);
        habit.setRepeatUnit(RepeatUnit.DAILY);
        habit.setRepeatScalar(1);

        // Sanity checks
        assertSameDay(now, habit.getAvailableAt());
        assertSameDay(getDate(1992, 1, 1), habit.getDueAt());

        Habits.completeHabit(habit);

        assertNotNull(habit.getLastCompletedAt());
        assertEquals(now, habit.getLastCompletedAt());
        assertEquals(nextAvailableAt(habit, now), habit.getAvailableAt());
        assertEquals(nextDueAt(habit, now), habit.getDueAt());

    }
}
