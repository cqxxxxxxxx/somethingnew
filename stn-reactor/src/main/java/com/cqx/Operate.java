package com.cqx;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/10/10
 */
public class Operate {

    @Test
    public void bufferAndBufferTimeOut() {

        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

        /**
         * 因为序列的生成是异步的，而转换成 Stream 对象可以保证主线程在序列生成完成之前不会退出，从而可以正确地输出序列中的所有元素
         */
        Flux.intervalMillis(100).bufferMillis(1001).take(2).toStream().forEach(System.out::println);

        Flux.intervalMillis(100).bufferMillis(1001).take(2).subscribe(System.out::println);

        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);

        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }


    @Test
    public void filter() {
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
    }


    /**
     * window 操作符的作用类似于 buffer，
     * 所不同的是 window 操作符是把当前流中的元素收集到另外的 Flux 序列中，因此返回值类型是 Flux<Flux<T>>
     */
    @Test
    public void window() {
        Flux.range(1, 100).window(20).subscribe(System.out::println);
        Flux.intervalMillis(100).windowMillis(1001).take(2).toStream().forEach(System.out::println);

    }


    @Test
    public void zipWith() {
        Flux.just("a", "b", "e")
                .zipWith(Flux.just("c", "d"))
//                .zipWith(Flux.just("e", "f"))
                .subscribe(System.out::println);
        Flux.just("a", "b", "e")
                .zipWith(Flux.just("c", "d", "f"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
    }

    /**
     * take 系列操作符用来从当前流中提取元素
     */
    @Test
    public void take() {
        Flux.range(1, 1000).take(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
    }

    @Test
    public void reduce() {
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

    }
}
