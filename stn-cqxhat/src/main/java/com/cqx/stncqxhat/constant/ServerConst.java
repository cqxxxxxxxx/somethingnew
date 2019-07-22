package com.cqx.stncqxhat.constant;

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

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 9001;
    public static final String SERVER_NAME = "cqxhat";
    public static final String VERSION = "0.0.1-alpha";
    public static final byte[] DELIMITER = new byte[]{'/', 's'};

    public static class Keys {
        /**
         * channelHandlerContext
         */
        public static final String CHC = "chc";
    }
}
