package com.springmvcrabbitmqexample.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrabbitmqexample.model.Person;


@RestController
public class TestController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
    @PostMapping(value = "/testPublishMessage", consumes = "application/json")
    public String testPublishMessage(@RequestBody Person person) {
    	System.out.println(person.getName());
    	rabbitTemplate.convertAndSend("exchangeTest", "testQueue", person);
        return "hello!!";
    }
}
