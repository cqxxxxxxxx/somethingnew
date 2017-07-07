package com.cqx.chapter5.recursiveAction;

import java.util.List;
import java.util.concurrent.*;

/**
 * {@link RecursiveAction} 没有返回值
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {

        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(100); //生成10000个产品
        Task task = new Task(products, 0, products.size(), 0.20);
        ForkJoinPool pool = new ForkJoinPool();

        pool.execute(task); //execute方法是异步调用的，会立刻返回。而invoke方法是同步的，即invoke方法需要等到传进 来的任务都完成才会返回。

        do {
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!task.isDone());

        pool.shutdown();

        if (task.isCompletedNormally()){
            System.out.printf("Main: The process has completed normally.\n");
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12){
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
            }
        }

        System.out.println("Main: End of the program.");
    }
}
