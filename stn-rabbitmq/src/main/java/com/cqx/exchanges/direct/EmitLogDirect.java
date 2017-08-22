package com.cqx.exchanges.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by BG307435 on 2017/8/18.
 */
public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //exchange direct模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String[] severityArray = {"info", "debug", "error"};
        String[] strArray = {"First message.", "Second message..", "Third message...", "Fourth message....", "Fifth message....."};

        Arrays.stream(strArray).forEach(x -> {
            String message = x;
            try {
                String severity = severityArray[new Random().nextInt(3)];
                //指定exchange 和 routing_key
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        channel.close();
        connection.close();
    }
}
