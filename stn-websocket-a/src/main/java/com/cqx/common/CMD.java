package com.cqx.common;

/**
 * Created by Shan on 2017/1/18.
 */
public class CMD {
    /**
     * 创建用户
     */
    public static final String USER_NAME_CREATE = "/USER_NAME_CREATE/";

    public static boolean isCMD(String message) {
        return message.startsWith(USER_NAME_CREATE);
    }
}
