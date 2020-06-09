package com.cqx.kafka.learning;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2020/4/6
 */
public class Receiver {
    KafkaConsumer<String, String> consumer;

    public Receiver() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "cqx4310");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "100000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test01"));
    }


    public void consume() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
        consumer.commitSync();
    }

    /**
     * 重置offset
     */
    public void resetOffset() {
        for (TopicPartition topicPartition : consumer.assignment()) {
            consumer.seek(topicPartition, 1);
        }
    }


    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        while (true) {
            receiver.resetOffset();
            receiver.consume();
        }
    }
}
