package com.cqx.chapter5.recursiveAction;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by cqx on 2017/2/24.
 */
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;

    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    /**
     * Reference Size 在这里设为10
     * 如果小于10就在当前线程中处理掉任务
     * 如果大于10则分解（Fork）任务并执行
     */
    @Override
    protected void compute() {
        if (last - first < 10){
            updatePrices();
        }else {
            int middle = (last + first) / 2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }
    }

    /**
     * 更新 first last之间的产品价格
     */
    private void updatePrices(){
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
