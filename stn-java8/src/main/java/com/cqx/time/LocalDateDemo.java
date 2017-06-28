package com.cqx.time;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

/**
 * java8的新的时间API {@link LocalDateTime}
 * 更多的使用 参考 @see http://www.jianshu.com/p/191f1f5ee82b
 *
 * Created by Shan on 2017/2/8.
 */
public class LocalDateDemo {

    /**
     *
     * 一些基础的操作
     * {@link LocalDate}        日期
     * {@link LocalTime}        时间
     * {@link LocalDateTime}    日期+时间
     * {@link Instant}
     */
    @Test
    public void basicTest(){
        LocalDate date = LocalDate.now();
        System.out.println(date);       //2017-02-09

        LocalTime time = LocalTime.now();
        System.out.println(time);       //18:10:36.493

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);   //2017-02-09T18:10:36.493

        Instant instant = Instant.now();
        Instant instant1 = dateTime.atZone(ZoneId.systemDefault()).toInstant(); //LocalDateTime转Instan
        System.out.println(instant);                //2017-02-09T10:18:16.849Z
        System.out.println(instant.toEpochMilli()); //转为unix timeStamp


        //向后兼容 对原有date calendar 添加了一些新方法使之能转为新的时间API
        Instant newDate1, newDate2;
        Date oldDate = new Date();
        newDate1 = oldDate.toInstant();  // date -> instant
        Calendar oldCalendar = Calendar.getInstance();
        newDate2 = oldCalendar.toInstant();      // calendar -> instant
        System.out.println(newDate1 + "_____" + newDate1);

    }


    /**
     * {@link java.time.temporal.TemporalAdjuster} 是个函数式接口
     * {@link TemporalAdjusters} 则提供了他的一些些实现    用于获取firstDayOfMonth之类的东西
     *
     * 日期的一些操作
     * 1.获取某天的开始结束时间
     * 2.获取某月的开始结束时间
     */
    @Test
    public void dateManipulation(){
        LocalDate today = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        //获取一天的开始与结尾
        LocalDateTime startOfToday = today.atStartOfDay();
        LocalDateTime endOfToday = today.atTime(LocalTime.MAX);

        //获取一个月的开始于结尾
        LocalDateTime firstDayOfMonth = dateTime.with(TemporalAdjusters.firstDayOfMonth());   //获取当前月的第一天
        LocalDateTime lastDayOfMonth = dateTime.with(TemporalAdjusters.lastDayOfMonth());     //获取当前月的最后一天

        //一些常量
        System.out.println(LocalTime.MAX);  //23:59:59.999999999
        System.out.println(LocalTime.MIN);  //00:00


        //加减运算
        dateTime.plus(2, ChronoUnit.WEEKS);    //加上2星期
        dateTime.minus(2, ChronoUnit.DAYS);    //减去2天
        dateTime.minusHours(2l);   //减去2小时
        dateTime.plusYears(1l);    //加上1年


    }

    @Test
    public void dateFormat(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate.toString());
        System.out.println(localDateTime.toString());

        System.out.println(localDate.format(DateTimeFormatter.ISO_DATE));
        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));


        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    }


    /**
     * {@link Period}   处理日期相关的时间段
     * {@link Duration} 处理时间相关的时间段
     */
    @Test
    public void periodAndDuration(){



    }

    /**
     * 杂乱无章的一些东西
     */
    @Test
    public void localDateTest(){

        //获取当前日期 不包括时分秒
        LocalDate today = LocalDate.now();

        System.out.println(today.toString());
        System.out.println(today.getYear() + "-" + today.getMonth().getValue() + "-" + today.getDayOfMonth());

        //Month 的加减
        Month thisMonth = today.getMonth();
        Month lastMonth = thisMonth.minus(1);
        System.out.println(lastMonth.getValue());

        //减少一个月后日期
        LocalDate date1 = today.minusMonths(1);
        System.out.println(date1.toString());

        // 获取当前月第一天和最后一天
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(firstDayOfMonth.toString());
        System.out.println(lastDayOfMonth.toString());

        //创建LocalTime 不包括日期
        LocalTime localTime = LocalTime.now();


        //创建LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();  //1
        LocalDateTime localDateTime1 = today.atTime(localTime); //2


        //创建Instant
        Date date = new Date();
        Instant instant1 = date.toInstant();    //java8支持的向后转型，提供给date转化为Instant的方法

        Instant instant = Instant.now();
        System.out.println(instant.toString());
        System.out.println(instant.toEpochMilli()); //unix时间戳 纳秒级别的

        //LocalDateTime 与 unix时间戳转换  -> 先转为 instant 然后获取long
        long unixTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();


    }
}
