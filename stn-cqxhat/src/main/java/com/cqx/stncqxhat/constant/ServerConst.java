package com.cqx.stncqxhat.constant;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public class ServerConst {

    private ServerConst() {
        throw new UnsupportedOperationException();
    }

    public static final String HOST = "192.168.124.12";
    public static final int PORT = 9001;
    public static final String SERVER_NAME = "cqxhat";
    public static final String VERSION = "0.0.1-alpha";
    public static final byte[] DELIMITER = new byte[]{'/', 's'};
    public static final String ENTER = "\r\n";
    public static final String BLANK = "";
    public static final User SYSTEM_USER = User.of("system", "hangzhou", null, 0, null);
    public static final Message EMPTY_MESSAGE;

    static {
        EMPTY_MESSAGE = new Message();
        EMPTY_MESSAGE.setFrom(SYSTEM_USER);
        EMPTY_MESSAGE.setMsg(BLANK);
    }
//    public static final String

    public static class Keys {
        /**
         * channelHandlerContext
         */
        public static final String CHC = "chc";
    }
}
