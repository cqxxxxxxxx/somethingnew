package com.cqx.system;


import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * Created by Shan on 2017/1/20.
 */
public class SystemBoom {

    /**
     * 输出系统属性
     */
    @Test
    public void systemProperties(){
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet){
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }


}
