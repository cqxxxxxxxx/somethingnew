package com.cqx.kafka.learning;

import org.apache.kafka.clients.consumer.ConsumerGroupMetadata;
import org.apache.kafka.clients.consumer.ConsumerPartitionAssignor;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class CustomAssignor implements ConsumerPartitionAssignor {

    /**
     * 设置自定义的消费者信息，比如所在的rackId机架信息
     * 从而在assign方法中可以获取到各个消费者的自定义信息，进行个性化处理
     * @param topics
     * @return
     */
    @Override
    public ByteBuffer subscriptionUserData(Set<String> topics) {
        return null;
    }

    /**
     * 消费者收到消费组leader 分配结果时的回调函数
     *
     * @param assignment
     * @param metadata
     */
    @Override
    public void onAssignment(Assignment assignment, ConsumerGroupMetadata metadata) {
        System.out.println(metadata.groupId());
        System.out.println(assignment.toString());
    }

    /**
     * 真正的分区分配方案实现
     * 实现组内广播，即组内所有消费者都消费订阅的topic的所有分区
     * @param metadata          集群元数据
     * @param groupSubscription 各个消费者的订阅信息
     * @return
     */
    @Override
    public GroupAssignment assign(Cluster metadata, GroupSubscription groupSubscription) {
        //查询消费组内所有订阅的主题topic
        Set<String> allSubscribedTopics = groupSubscription.groupSubscription().entrySet()
                .stream().flatMap(x -> x.getValue().topics().stream()).collect(Collectors.toSet());
        //把所有订阅的topic的分区都放一起
        List<TopicPartition> allPartitions = allSubscribedTopics.stream().flatMap(x -> metadata.partitionsForTopic(x).stream())
                .map(x -> new TopicPartition(x.topic(), x.partition())).collect(Collectors.toList());
        Map<String, Assignment> assignments = new HashMap<>();
        for (String memberId : groupSubscription.groupSubscription().keySet()) {
            //每个消费者设置分配规则， assignment
            assignments.put(memberId, new Assignment(allPartitions));
        }
        //返回消费者的 消费规则配置
        GroupAssignment groupAssignment = new GroupAssignment(assignments);
        return groupAssignment;
    }

    @Override
    public String name() {
        return "custom";
    }
}
