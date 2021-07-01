package com.cqx;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/10/10
 */
public class FluxAndMono {

    /**
     * 简单序列生成
     *
     * @throws InterruptedException
     */
    @Test
    public void FluxSimple() throws InterruptedException {
        Flux.just("cqx", "wu", "di").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        /**
         * 10秒一次
         */
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        /**
         * 一秒一次
         */
        Flux.intervalMillis(1000).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(60);
    }


    @Test
    public void FluxComplex() {
//      generate创建
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);


//      create创建
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }


    @Test
    public void mono() {
        Mono.just("aaa").map(x -> x.length()).subscribe(x -> System.out.println(x));
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.error(new Exception("bbbb"))).subscribe(System.out::println);
    }
}
