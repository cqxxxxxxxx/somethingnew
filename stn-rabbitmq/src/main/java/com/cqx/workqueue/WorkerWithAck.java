package com.cqx.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 接受处理完消息后返回ack 给rabbitmq 从而确保消息已被消费，从而把message从queue里删除
 * 如果没有返回ack则 rabbitmq会重新把该message发给其他消费者
 * Created by BG307435 on 2017/8/17.
 */
public class WorkerWithAck {

    private final static String QUEUE_NAME = "durable_queue";
//    private final static String DURABLE_QUEUE_NAME = "durable_queue";


    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();    //socket连接建立
        Channel channel = connection.createChannel();

        //it will only be created if it doesn't exist already
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //一次只接受一条消息，只有一条处理完，返回ack给rabbitmq，才可能会收到下一条消息
        channel.basicQos(1); // accept only one unack-ed message at a time (see below)
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                    //An ack(nowledgement) is sent back from the consumer to tell RabbitMQ that a particular message has been received, processed and that RabbitMQ is free to delete it.
                    channel.basicAck(envelope.getDeliveryTag(), false); //返回确认处理完毕的信息
                }
            }
        };

        channel.basicConsume(QUEUE_NAME, consumer);
    }


    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
