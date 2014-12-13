package com.zacharytamas.often.models;

import android.test.InstrumentationTestCase;

import java.util.GregorianCalendar;

/**
 * Created by zacharytamas on 12/12/14.
 */
public class HabitTest extends InstrumentationTestCase {

    Habit habit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        habit = new Habit();
    }

    public void test_setRepeatOnWeekday() {

        assertEquals(habit.getRepeatWeekdays(), 0);

        assertFalse(habit.getRepeatsOnWeekday(GregorianCalendar.SUNDAY));
        habit.setRepeatOnWeekday(GregorianCalendar.SUNDAY, true);
        assertTrue(habit.getRepeatsOnWeekday(GregorianCalendar.SUNDAY));
        habit.setRepeatOnWeekday(GregorianCalendar.SUNDAY, false);
        assertFalse(habit.getRepeatsOnWeekday(GregorianCalendar.SUNDAY));

        assertFalse(habit.getRepeatsOnWeekday(GregorianCalendar.MONDAY));
        habit.setRepeatOnWeekday(GregorianCalendar.SUNDAY, true);
        habit.setRepeatOnWeekday(GregorianCalendar.MONDAY, true);
        assertTrue(habit.getRepeatsOnWeekday(GregorianCalendar.SUNDAY));

    }

}
