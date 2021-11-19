package com.springbootrabbitmqexample.controllers;

import com.springbootrabbitmqexample.model.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {


    @RabbitListener(queues = "testQueue")
    public void getMessageFromTestQueue(Person person) {
        System.out.println("obtained message from testQueue is ===================> "+person.getName());
    }

    @RabbitListener(queues = "header1")
    public void getMessageFromHeader1(byte[] data) {
        System.out.println("obtained message from header1 is ===================> "+new String(data));
    }

    @RabbitListener(queues = "header2")
    public void getMessageFromHeader2(byte[] data) {
        System.out.println("obtained message from header2 is ===================> "+new String(data));
    }

    @RabbitListener(queues = "header3")
    public void getMessageFromHeader3(byte[] data) {
        System.out.println("obtained message from header3 is ===================> "+new String(data));
    }

}
