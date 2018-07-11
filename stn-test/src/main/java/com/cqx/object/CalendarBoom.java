package com.cqx.object;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by BG307435 on 2017/9/29.
 */
public class CalendarBoom {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void test1() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println(dateFormat.format(calendar.getTime()));
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        System.out.println(dateFormat.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        System.out.println(dateFormat.format(calendar.getTime()));

    }

    @Test
    public void lastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 2, 28);
        cal.add(Calendar.MONTH, -1);
        System.out.println(df.format(cal.getTime()));
        cal.set(2017, 3, 30);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 2);
        System.out.println(df.format(cal.getTime()));
        System.out.println((double) 120);
    }
}
