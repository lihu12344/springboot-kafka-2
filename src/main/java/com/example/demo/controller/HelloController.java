package com.example.demo.controller;

import com.example.demo.producer.ProducerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private ProducerService producerService;

    @RequestMapping("/sendInorder")
    public String hello(){
        try{
            producerService.sendInOrder();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "success";
    }

    @RequestMapping("/sendInTransaction")
    public String hello2(){
        try{
            producerService.sendInTransaction();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "success 2";
    }

    @RequestMapping("/sendInBatch")
    public String hello3(){
        producerService.sendInBatch();

        return "success 3";
    }

    @RequestMapping("/sendInBatchTest")
    public String hello4(){
        producerService.sendInBatchTest();

        return "success 4";
    }

    @RequestMapping("/sendSync")
    public String hello5(){
        try {
            producerService.sendSync();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "success 5";
    }
}