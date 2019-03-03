package com.cqx.lock;

import redis.clients.jedis.Jedis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/3
 */
public class SerialNoUtil {

    String serialKey = "";

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("YYYYMMdd");
        System.out.println(df.format(new Date()));
        System.out.println(String.format("%05d", 111));
        System.out.println(String.format("%05d", 12211));
        SerialNoUtil serialNoUtil = new SerialNoUtil();
        for (int i = 0; i < 1000; i++) {
            System.out.println(serialNoUtil.getSerialNo());
        }
    }


    public String getSerialNo() {
        Jedis jedis = new Jedis("localhost", 6379);
        DateFormat df = new SimpleDateFormat("YYYYMMdd");
        String serialKey = df.format(new Date());
        String a = jedis.get(serialKey);
        if (a == null) {
            jedis.set(serialKey, "1", "NX", "EX", 60 * 60 * 24);
            return serialKey + "00001";
        }
        Long no = jedis.incr(serialKey);
        return serialKey + String.format("%05d", no);
    }
}
