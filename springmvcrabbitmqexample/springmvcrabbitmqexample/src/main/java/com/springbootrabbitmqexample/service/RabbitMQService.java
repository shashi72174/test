package com.springbootrabbitmqexample.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springbootrabbitmqexample.model.Person;

@Service
@EnableRabbit
public class RabbitMQService {
	@RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = "testQueue")
	public 	void getMessage(Person person) {
		System.out.println("came here");
		System.out.println(person);
	}
}
