package com.cqx.date;


import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * JAVA8 的时间范围工具类
 * Created by cqx on 2017/6/27.
 */
public class DateUtilJ8 {


    public static <T> TimePair getTimePair(T t, int prev, int next){
        if (t instanceof Long){
            return getTimeRangeFixed((Long) t, prev, next);
        }
        if (t instanceof Date){
            return getTimeRangeFixed((Date) t, prev, next);
        }
        return null;
    }

    /**
     * 一周的时间范围
     * TemporalField：A field of date-time, such as month-of-year or hour-of-minute.
     * Temporal:时间的  Field:域
     * @param timestamp
     * @return
     */
    public static TimePair getWeekTimeRange(long timestamp){
        TimePair timePair = new TimePair();
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate dayBegin = localDateTime.toLocalDate();

        TemporalField fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek();
        LocalDate weekBegin = dayBegin.with(fieldISO, 1);
        LocalDate weekEnd = dayBegin.with(fieldISO, 7);

        timePair.setStartTime(weekBegin.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        timePair.setEndTime(weekEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        return timePair;
    }

    /**
     * 一月的时间范围
     * @param timestamp
     * @return
     */
    public static TimePair getMonthTimeRange(long timestamp){
        TimePair timePair = new TimePair();
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        LocalDateTime firstDayOfMonth = localDateTime.with(TemporalAdjusters.firstDayOfMonth());   //获取当前月的第一天
        LocalDateTime lastDayOfMonth = localDateTime.with(TemporalAdjusters.lastDayOfMonth());     //获取当前月的最后一天

        timePair.setStartTime(firstDayOfMonth.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        timePair.setEndTime(lastDayOfMonth.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        return timePair;
    }


    /**
     * 一天的时间范围
     * @param timestamp
     * @return
     */
    public static TimePair getDayTimeRange(long timestamp){
        TimePair timePair = new TimePair();
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        LocalDate dayBegin = localDateTime.toLocalDate();
        LocalDate dayNextBegin = dayBegin.plusDays(1);

        timePair.setStartTime(dayBegin.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        timePair.setEndTime(dayNextBegin.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        return timePair;
    }


    /**
     * Date 类型 获取指定前几天 后几天
     * @param date
     * @param prev
     * @param next
     * @return
     */
    public static TimePair getTimeRangeFixed(Date date, int prev, int next){
        long trans = date.getTime();
        return getTimePair(trans, prev, next);
    }

    /**
     * 获取 指定时间 前几天 后几天的开始时间戳
     * @param unixTime
     * @param prev
     * @param next
     * @return
     */
    public static TimePair getTimeRangeFixed(long unixTime, int prev, int next) {
        TimePair timePair = new TimePair();
        Instant instant = Instant.ofEpochMilli(unixTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate dayBegin = localDateTime.toLocalDate();

        LocalDate startDate = dayBegin.minusDays(prev);
        LocalDate endDate = dayBegin.plusDays(next);

        timePair.setStartTime(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        timePair.setEndTime(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        return timePair;
    }


    /**
     * unixTime 转 LocalDateTime
     * @param timestamp 时间戳
     * @return
     */
    public static LocalDateTime getDateTimeFromTimestamp(long timestamp) {
        if (timestamp == 0)
            return null;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
                .getDefault().toZoneId());
    }

    /**
     * unixTime 转 LocalDate
     * @param timestamp 时间戳
     * @return
     */
    public static LocalDate getDateFromTimestamp(long timestamp) {
        LocalDateTime date = getDateTimeFromTimestamp(timestamp);
        return date == null ? null : date.toLocalDate();
    }

    @Test
    public void test(){
        long now = System.currentTimeMillis();
        TimePair timePair = getTimePair(now, 10, 5);
        System.out.println(timePair.getStartTime());
        System.out.println(timePair.getEndTime());
        timePair = getWeekTimeRange(now);
        System.out.println(timePair.getStartTime());
        System.out.println(timePair.getEndTime());

    }
}
