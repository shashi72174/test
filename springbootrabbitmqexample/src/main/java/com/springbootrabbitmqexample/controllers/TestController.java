package com.springbootrabbitmqexample.controllers;

import com.springbootrabbitmqexample.model.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

@RestController
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/people")
    public String publishMessage(@RequestBody Person person) {
        //System.out.println(person.getName());
        rabbitTemplate.convertAndSend("exchangeTest", "testQueue", person);
        rabbitTemplate.convertAndSend("fanoutExchange", "", person);
        rabbitTemplate.convertAndSend("topicExchange", "topic1.topic2.topic3", person);
        return "SUCCESS";
    }


    @PostMapping("/people_new")
    public String publishMessageNew(@RequestBody Person person) throws Exception {
        //System.out.println(person.getName());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(person);

        Message message = MessageBuilder.withBody(byteArrayOutputStream.toByteArray()).setHeader("item1", "mobile").setHeader("item2","ac").build();
        rabbitTemplate.send("headerExchange", "", message);
        //rabbitTemplate.convertAndSend("fanoutExchange", "", person);
        //rabbitTemplate.convertAndSend("topicExchange", "topic1.topic2.topic3", person);
        return "SUCCESS";
    }
}
