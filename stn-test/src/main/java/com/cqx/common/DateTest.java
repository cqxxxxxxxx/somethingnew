package com.cqx.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BG307435 on 2017/12/5.
 */
public class DateTest {

    @Test
    public void diffTest() {
        Date now = new Date();
        Date now1 = new Date();
        System.out.println(now.before(now));
        System.out.println(now.after(now));
        System.out.println(now.equals(now1));
    }

    @Test
    public void calendarTest() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.getTime());
    }

    @Test
    public void jackFormatTest() {
        Person person = new Person();
        String date = "2017-12-6 10:00:00";
        ObjectMapper objectMapper = new ObjectMapper();
        Date date1 = objectMapper.convertValue(date, Date.class);
        System.out.println(date1);
    }

    private class Person {
        private String name;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date birthday;
    }
}
