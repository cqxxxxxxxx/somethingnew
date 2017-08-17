package com.cqx.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Created by BG307435 on 2017/8/17.
 */
public class NewTask {

    private final static String QUEUE_NAME = "hello";
    private final static String DURABLE_QUEUE_NAME = "durable_queue";   //durable的时候还要设置 持久化方式 这里是MessageProperties.PERSISTENT_TEXT_PLAIN
    private final static boolean DURABLE = true;     //设置message 可以持久化 保存到disk

    public static void main(String[] args) throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();    //socket连接建立
        Channel channel = connection.createChannel();



        //it will only be created if it doesn't exist already
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueDeclare(DURABLE_QUEUE_NAME, DURABLE, false, false, null);

        String[] strArray = {"First message.", "Second message..", "Third message...", "Fourth message....", "Fifth message....."};

        Arrays.stream(strArray).forEach(x -> {
            String message = x;
            try {
                //durable的时候还要设置 持久化方式 这里是MessageProperties.PERSISTENT_TEXT_PLAIN
                channel.basicPublish("", DURABLE_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());     //byte array
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(" [x] Sent '" + message + "'");
        });

        channel.close();
        connection.close();
    }



    private static String getMessage(String[] strings){
        if (strings.length < 1) {
            return "noob !";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] args, String delimiter) {
        return Arrays.stream(args).collect(Collectors.joining(delimiter)).toString();
    }
}
