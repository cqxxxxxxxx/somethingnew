package com.cqx.object;

import org.testng.annotations.Test;

import java.util.HashMap;
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
}
