package com.cqx.exchanges.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;

/**
 * Created by BG307435 on 2017/8/18.
 */
public class RecvLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        String[] severityArray = {"info", "debug", "error"};
        for(String severity : severityArray){
            if (new Random().nextBoolean()) {
                //把exchange中关心的 routing_key 绑定到队列中
                //通过该exchange 的所有被bind的routing_key都会推到这个队列里。
                channel.queueBind(queueName, EXCHANGE_NAME, severity);
            }
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
