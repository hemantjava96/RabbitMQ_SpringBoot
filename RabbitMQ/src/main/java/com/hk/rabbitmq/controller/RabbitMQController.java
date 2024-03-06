package com.hk.rabbitmq.controller;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.rabbitmq.entity.File;

@RestController
@RequestMapping("/rmq/publish")
public class RabbitMQController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("/message/{type}")
	public String publishName(@PathVariable("type") String type) {
		File file = new File();
		file.setFileID(1);
		file.setFileType(type);

		// directly route to queue
		rabbitTemplate.convertAndSend("queue-name1", file);

		// route to direct exchange
		rabbitTemplate.convertAndSend("direct-exchange", "xmlKey", file);

		// route to fanout exchange
		rabbitTemplate.convertAndSend("fanout-exchange", "", file);

		// route to topic exchange
		rabbitTemplate.convertAndSend("topic-exchange", "html.xml.excel", file);

		return "Success";

	}

	
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/header/{type}")
	public String testAPI(@PathVariable("type") String type) throws IOException {
		File f = new File(1, type);

		// Serialize File object to JSON
		String jsonFile = objectMapper.writeValueAsString(f);
		
		Message message = MessageBuilder.withBody(jsonFile.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("key", "header")
				// .setHeader("key2", "value2")
                .build();
		
		rabbitTemplate.send("headers-exchange", "", message);
		return "Success";
	}

}
