package com.example.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    @KafkaListener(groupId = "consumerGroup",topics = "order")
    public void consume(ConsumerRecords<Object,String> consumerRecords){
        for(TopicPartition topicPartition:consumerRecords.partitions()){
            for(ConsumerRecord<Object,String> consumerRecord:consumerRecords.records(topicPartition)){
                System.out.println("消费时间："+System.currentTimeMillis()+" "+consumerRecord.value());
            }
        }
    }

    @KafkaListener(groupId = "consumerGroup2",topics = "transaction")
    public void consume2(ConsumerRecords<Object,String> consumerRecords){
        for(TopicPartition topicPartition:consumerRecords.partitions()){
            for(ConsumerRecord<Object,String> consumerRecord:consumerRecords.records(topicPartition)){
                System.out.println("消费时间："+System.currentTimeMillis()+" "+consumerRecord.value());
            }
        }
    }

    @KafkaListener(groupId = "consumerGroup3",topics = "batch")
    public void consume3(ConsumerRecords<Object,String> consumerRecords){
        for(TopicPartition topicPartition:consumerRecords.partitions()){
            for(ConsumerRecord<Object,String> consumerRecord:consumerRecords.records(topicPartition)){
                System.out.println("消费时间："+System.currentTimeMillis()+" "+consumerRecord.value());
            }
        }
    }

    @KafkaListener(groupId = "consumerGroup4",topics = "sync")
    public void consume4(ConsumerRecords<Object,String> consumerRecords, Acknowledgment acknowledgment){
        for(TopicPartition topicPartition:consumerRecords.partitions()){
            for(ConsumerRecord<Object,String> consumerRecord:consumerRecords.records(topicPartition)){
                System.out.println("消费时间："+System.currentTimeMillis()+" "+consumerRecord.value());
            }
            acknowledgment.acknowledge();
        }
    }

}