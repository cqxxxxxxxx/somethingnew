package com.cqx.chapter5.recursiveAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqx on 2017/2/24.
 */
public class ProductListGenerator {

    /**
     * 生成指定大小的产品列表
     * @param size
     * @return
     */
    public List<Product> generate(int size){
        List<Product> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product" + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
