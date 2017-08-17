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
     * 字段	 	允许值	 	允许的特殊字符
     * 秒	 	0-59	 	, - * /
     * 分	 	0-59	 	, - * /
     * 小时	 	0-23	 	, - * /
     * 日期	 	1-31	 	, - *   / L W C
     * 月份	 	1-12 或者 JAN-DEC	 	, - * /
     * 星期	 	1-7 或者 SUN-SAT	 	, - *   / L C #
     * 年（可选）	 	留空, 1970-2099	 	, - * /
     *
     "/" 字符表示增量值。例如，在秒字段中“5/15”代表从第 5 秒开始，每 15 秒一次; "/15, 则表示开始就触发，然后每15S一次"。
     “*”字符被用来指定所有的值。如：”*“在分钟的字段域里表示“每分钟”。
     “-”字符被用来指定一个范围。如：“10-12”在小时域意味着“10点、11点、12点”。
     “,”字符被用来指定另外的值。如：“MON,WED,FRI”在星期域里表示”星期一、星期三、星期五”.
     “?”字符只在日期域和星期域中使用。它被用来指定“非明确的值”。当你需要通过在这两个域中的一个来指定一些东西的时候，它是有用的。看下面的例子你就会明白。
     “L”字符指定在月或者星期中的某天（最后一天）。即“Last ”的缩写。但是在星期和月中“Ｌ”表示不同的意思，如：在月子段中“L”指月份的最后一天-1月31日，2月28日，如果在星期字段中则简单的表示为“7”或者“SAT”。如果在星期字段中在某个value值得后面，则表示“某月的最后一个星期value”,如“6L”表示某月的最后一个星期五。
     “W”字符只能用在月份字段中，该字段指定了离指定日期最近的那个星期日。
     “#”字符只能用在星期字段，该字段指定了第几个星期value在某月中
     **/
    @Scheduled(cron="*/5 * * * * MON-FRI")
    public void doSomething4() {
        System.out.println("cron");
        // something that should execute on weekdays only
    }

}
