package com.example.demo.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProducerService {

    @Resource
    private KafkaTemplate<Object,String> kafkaTemplate;

    public void sendSync() throws Exception{
        for (int i=0;i<100;i++){
            SendResult<Object,String> result=kafkaTemplate.send("sync","瓜田李下 同步发送"+i).get();
            System.out.println(result);
        }
    }

    public void sendInOrder() throws Exception {
        for (int i=0;i<100;i++){
            SendResult<Object,String> result=kafkaTemplate.send("order",0,null,"瓜田李下 顺序消息"+i).get();
            System.out.println("发送时间："+System.currentTimeMillis()+"  "+result);
        }
    }

    public void sendInTransaction() throws Exception {
        for (int i=0;i<100;i++){
            int finalI = i;
            String result=kafkaTemplate.executeInTransaction(kafkaOperations -> {
                try {
                    if (finalI ==2){
                        throw new Exception("出错了");
                    }

                    System.out.println(kafkaOperations.send("transaction","瓜田李下 事务消息"+finalI).get());

                    return "发送成功";
                }catch (Exception e){
                    e.printStackTrace();
                    return "发送失败";
                }
            });

            System.out.println("消息 "+i+"发送结果为："+result);
        }
    }

    public void sendInBatch(){
        long start=System.currentTimeMillis();
        System.out.println("消息开始发送："+start);

        kafkaTemplate.execute(producer -> {
            for (int i=0;i<1000;i++){
                ProducerRecord<Object,String> producerRecord=new ProducerRecord<>("batch","瓜田李下 批量发送"+i);
                producer.send(producerRecord);
            }
            return null;
        });

        long end=System.currentTimeMillis();
        System.out.println("消息发送结束："+end);
        System.out.println("发送消息耗时："+(end-start)+"毫秒");
    }

    public void sendInBatchTest(){
        long start=System.currentTimeMillis();
        System.out.println("批量消息测试发送开始："+start);

        for (int i=0;i<1000;i++){
            kafkaTemplate.send("batch","瓜田李下 批量消息测试"+i);
        }

        long end=System.currentTimeMillis();
        System.out.println("批量消息测试发送结束："+end);
        System.out.println("发送消息耗时："+(end-start)+"毫秒");
    }
}