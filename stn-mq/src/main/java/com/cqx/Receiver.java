package com.cqx;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Created by cqxxxxx on 2017/1/17.
 */
public class Receiver implements MessageListener{
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    //中间件即消息服务的地址 设为默认的url 即没做改动的  "failover://tcp://localhost:61616"
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String SUBJECT = "my-activemq";    //创建的mq名字

    public static void main(String[] args) throws JMSException, InterruptedException {
        //初始化连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //建立连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式,这里是自动获取
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目标队列
        Destination dest = session.createQueue(SUBJECT);
        //通过session创建消息接受者
        MessageConsumer consumer = session.createConsumer(dest);
        //初始化监听器
        Receiver receiver = new Receiver();
        //给接受者添加监听器
        consumer.setMessageListener(receiver);

//        connection.close();

    }


    @Override
    public void onMessage(Message arg0) {
        TextMessage message = (TextMessage) arg0;
        try {
            logger.debug("收到消息:" + message.getText());
            System.out.println("收到消息:" + message.getText());
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
        }

    }
}
