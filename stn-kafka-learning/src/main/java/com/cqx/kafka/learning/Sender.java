package com.cqx.kafka.learning;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2020/4/6
 */
public class Sender implements Closeable {
    Producer<String, String> producer;

    public Sender() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void send(String msg) {
        producer.send(new ProducerRecord<String, String>("test01", System.currentTimeMillis() + "", msg));
    }


    public static void main(String[] args) throws InterruptedException {
        Sender sender = new Sender();
        for (int i = 0; i < 100; i++) {
            sender.send(i + "");
        }
        TimeUnit.SECONDS.sleep(5);
    }

    @Override
    public void close() throws IOException {
        producer.close();
    }
}
