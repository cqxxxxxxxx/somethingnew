package com.cqx.object;



import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shan on 2017/1/20.
 */
public class ObjectBoom {

    @Test
    public void objectName(){
        ObjectBoom boom = new ObjectBoom();
        Class clazz = boom.getClass();
        System.out.println(clazz.getName());    //com.cqx.object.ObjectBoom
        System.out.println(clazz.getSimpleName());  //ObjectBoom

    }

    @Test
    public void collectionTest(){
        Map map = new HashMap();
        Object o = map.get("saa");
        System.out.println(o);  //null
    }

    @Test
    public void listTest(){
        List<Integer> integers = new ArrayList<Integer>();
        System.out.println(integers);
    }
}
