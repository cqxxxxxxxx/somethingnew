package com.cqx.object;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    @Test
    public void dateClone() {
        Date date = new Date();
        System.out.println(df.format(date));
        Date date1 = (Date) date.clone();
        date1.setYear(1111);
        System.out.println(df.format(date));
        System.out.println(df.format(date1));
    }

    @Test
    public void day31() {
        Date endDate = null;
        Calendar cal = Calendar.getInstance();
        if (endDate == null) {
            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
        } else {
            cal.setTime(endDate);
        }
        Date endTime = cal.getTime();
        cal.setTime(endTime);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 30);
        Date startTime = cal.getTime();
        System.out.println(df.format(endTime));
        System.out.println(df.format(startTime));
    }
}
