package com.cqx.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

/**
 * Created by BG307435 on 2017/8/7.
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();    //socket连接建立
        Channel channel = connection.createChannel();

        //it will only be created if it doesn't exist already
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());     //byte array
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
