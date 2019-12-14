package com.cqx;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DegradeTest {
    public static void main(String[] args) throws InterruptedException {
        // 配置规则.
        initDegradeRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world");
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (DegradeException ex) {
                // 熔断器打开， 进行降级逻辑
                System.out.println("circuit open, fallback called");
            } catch (BlockException ex) {
                // 其他block异常
                System.out.println("blocked!");
            }
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    private static void initDegradeRules() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("HelloWorld");
        //响应时间来降级
        /**
         * 平均响应时间 (DEGRADE_GRADE_RT)：
         * 当 1s 内持续进入 5 个请求，对应时刻的平均响应时间（秒级）均超过阈值（count，以 ms 为单位），
         * 那么在接下的时间窗口（DegradeRule 中的 timeWindow，以 s 为单位）之内，对这个方法的调用都会自动地熔断（抛出 DegradeException）。
         * 注意 Sentinel 默认统计的 RT 上限是 4900 ms，超出此阈值的都会算作 4900 ms，若需要变更此上限可以通过启动配置项 -Dcsp.sentinel.statistic.max.rt=xxx 来配置。
         */
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setTimeWindow(10); //熔断器保持开启10s， 所有调用抛出DegradeException，然后进行降级逻辑
        rule.setCount(100); //1S内进入5个请求 且平均响应时间大于100ms 则进行熔断
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
