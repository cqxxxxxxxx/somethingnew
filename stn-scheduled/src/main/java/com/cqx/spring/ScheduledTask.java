package com.cqx.spring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 还可以通过 zone 来设置时区
 * Created by admin on 2017/3/13.
 */
@Component
public class ScheduledTask {

    /**
     * 在上一次执行完成后过5s执行
     */
    @Scheduled(fixedDelay = 5000)
    public void doSomething1(){
        System.out.println("fixedDelay");
    }

    /**
     * 每隔5s执行一次
     */
    @Scheduled(fixedRate=5000)
    public void doSomething2() {
        System.out.println("fixedRate");
        // something that should execute periodically
    }

    @Scheduled(initialDelay=1000, fixedRate=5000)
    public void doSomething3() {
        System.out.println("initialDelay fixedRate");
    }

    /**
     * cron 来配置
     */
    @Scheduled(cron="*/5 * * * * MON-FRI")
    public void doSomething4() {
        System.out.println("cron");
        // something that should execute on weekdays only
    }

}
