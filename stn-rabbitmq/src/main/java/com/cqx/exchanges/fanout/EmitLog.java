package com.cqx.exchanges.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * exchange fanout 模式， 一般用于广播
 * it just broadcasts all the messages it receives to all the queues it knows.
 * Created by BG307435 on 2017/8/18.
 */
public class EmitLog {


    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        String[] strArray = {"First message.", "Second message..", "Third message...", "Fourth message....", "Fifth message....."};

        Arrays.stream(strArray).forEach(x -> {
            String message = x;
            try {
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        channel.close();
        connection.close();
    }


}
