package com.cqx.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CompeletableFutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//         test0();
        test1();
//        test2();
//        test3();
    }


    private static void test0() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        assertEquals("Hello", future.get());
    }

    private static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            return "Hello";
        });

        CompletableFuture<String> future = completableFuture
                .thenApply(s -> {
                    System.out.println(Thread.currentThread());
                    return s + " World";
                });
        System.out.println(Thread.currentThread());
        assertEquals("Hello World", future.get());
    }

    private static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync" + System.currentTimeMillis() + Thread.currentThread().toString());
            return "Hello";
        })
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("thenCombine" + System.currentTimeMillis() + Thread.currentThread().toString());
                    return s + " World";
                }));

        assertEquals("Hello World", completableFuture.get());
    }

    private static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync" + System.currentTimeMillis() + Thread.currentThread().toString());
            return "Hello";
        })
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                            System.out.println("thenCombine" + System.currentTimeMillis() + Thread.currentThread().toString());
                            return " World";
                        }),
                        (s1, s2) -> {
                            System.out.println("biFunction" + System.currentTimeMillis() + Thread.currentThread().toString());
                            return s1 + s2;
                        });
        assertEquals("Hello World", completableFuture.get());
    }
}
