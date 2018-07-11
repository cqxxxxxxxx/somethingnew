package com.cqx.helloworld.senderconfirmmode;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cqx on 2018/7/11.
 */
public class SenderWithConfirm {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws java.io.IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("47.92.6.210");
        factory.setPort(8080);
        Connection connection = factory.newConnection();    //socket连接建立
        Channel channel = connection.createChannel();

//      设置当前Channel信道设置成confirm模式 消息投递后会回发ID 如果收到说明投递成功，否则失败
//        Enables publisher acknowledgements on this channel.
        channel.confirmSelect();

//      添加 rabbitmq sender投递消息后的confirm回应的监听器
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("confirm模式正常运作 收到confirm");

//              deliveryTag 在同一个channel中 依次增加
                System.out.println("confirm Id:" + deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("rabbitmq 内部错误 消息丢失噜噜噜");
            }
        });

        //it will only be created if it doesn't exist already
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());     //byte array

//      在waitForConfirms方法上阻塞， 1000ms后还没收到回应则返回false
        if (channel.waitForConfirms(1000L)) {
            System.out.println("消息投递成功咯");
        } else {
            System.out.println("消息投递失败咯 崽总");
        }


        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
