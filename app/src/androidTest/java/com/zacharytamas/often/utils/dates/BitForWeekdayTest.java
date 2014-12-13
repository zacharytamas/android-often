package com.zacharytamas.often.utils.dates;

import android.test.InstrumentationTestCase;

import com.zacharytamas.often.utils.Dates;

import java.util.GregorianCalendar;

import static com.zacharytamas.often.utils.Dates.getBitForWeekday;
import static com.zacharytamas.often.utils.Dates.setBitForWeekday;

/**
 * Created by zacharytamas on 12/12/14.
 */
public class BitForWeekdayTest extends InstrumentationTestCase {

    public void test_setBitTrue() {

        byte mask = 0;

        // TODO Make this more exhaustive.

        assertEquals(setBitForWeekday(mask, GregorianCalendar.SUNDAY, true), 1);
        assertEquals(setBitForWeekday(mask, GregorianCalendar.MONDAY, true), 2);
        assertEquals(setBitForWeekday(mask, GregorianCalendar.TUESDAY, true), 4);
        assertEquals(setBitForWeekday(mask, GregorianCalendar.WEDNESDAY, true), 8);
        assertEquals(setBitForWeekday(mask, GregorianCalendar.THURSDAY, true), 16);
        assertEquals(setBitForWeekday(mask, GregorianCalendar.FRIDAY, true), 32);
        assertEquals(setBitForWeekday(mask, GregorianCalendar.SATURDAY, true), 64);

        // Test setting two bits at once.
        mask = setBitForWeekday(mask, GregorianCalendar.SUNDAY, true);
        mask = setBitForWeekday(mask, GregorianCalendar.SATURDAY, true);
        assertEquals(mask, (byte) (Math.pow(2, GregorianCalendar.SATURDAY - 1) + 1));

    }

    public void test_setBitFalse() {

        byte mask = 1;

        assertEquals(setBitForWeekday(mask, GregorianCalendar.SUNDAY, false), 0);

        mask = setBitForWeekday(mask, GregorianCalendar.MONDAY, true);
        mask = setBitForWeekday(mask, GregorianCalendar.SUNDAY, true);

        assertEquals(setBitForWeekday(mask, GregorianCalendar.SUNDAY, false), 2);
    }

    public void test_getBit() {
        byte mask = 2;
        assertTrue(getBitForWeekday(mask, GregorianCalendar.MONDAY));
        assertFalse(getBitForWeekday(mask, GregorianCalendar.SUNDAY));
        assertFalse(getBitForWeekday(mask, GregorianCalendar.TUESDAY));
    }

}
