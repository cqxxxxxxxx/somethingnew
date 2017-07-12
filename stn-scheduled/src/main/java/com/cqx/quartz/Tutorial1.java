package com.cqx.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by cqx on 2017/6/10.
 */
public class Tutorial1 {

    @Test
    public void t1() throws SchedulerException, InterruptedException {
        SchedulerFactory schedFact = new StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();

        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "group1") // name "myJob", group "group1"
                .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(4)
                        .repeatForever())
                .build();

        //cronTrigger set the job run 每秒一次，在１５分钟和４５分钟内
        Trigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myCronTrigger", "group1")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("* 15,45,29 * * * ? *"))
                .build();

        // Tell quartz to schedule the job using our trigger
//        sched.scheduleJob(job, trigger);
        sched.scheduleJob(job, cronTrigger);
        TimeUnit.SECONDS.sleep(30);
        sched.shutdown();
    }




}
