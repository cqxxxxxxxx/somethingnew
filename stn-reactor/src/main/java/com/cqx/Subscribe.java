package com.cqx;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/10/10
 */
public class Subscribe {

    @Test
    public void subscribe() {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);

        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);

        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .switchOnError(Mono.just(0))
                .subscribe(System.out::println);

        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResumeWith(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);

        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);
    }


    @Test
    public void scheduler() throws InterruptedException {
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }

    @Test
    public void hot() throws InterruptedException {
        final Flux<Long> source = Flux.intervalMillis(1000)
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe();
        Thread.sleep(5000);
        source
                .toStream()
                .forEach(System.out::println);
    }
}
