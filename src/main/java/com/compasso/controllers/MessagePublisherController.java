package com.compasso.controllers;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.configs.MQConfig;
import com.compasso.models.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="API RabbitMQ Publisher")
@CrossOrigin(origins="*")
@RestController
public class MessagePublisherController {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/publish")
	@ApiOperation(value="Enviar produto.")
	public Product publishProduct(@RequestBody Product prod) {
		prod.setId(UUID.randomUUID().toString());
		template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, prod);
		
		return prod;
	}
}
