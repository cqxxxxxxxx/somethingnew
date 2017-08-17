package com.cqx.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BG307435 on 2017/8/17.
 */
public class Recv {

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
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Since it will push us messages asynchronously, we provide a callback in the form of
        // an object that will buffer the messages until we're ready to use them.
        // That is what a DefaultConsumer subclass does.
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        channel.basicConsume(QUEUE_NAME, consumer);
    }
}
