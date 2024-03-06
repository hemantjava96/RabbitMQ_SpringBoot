package com.hk.rabbitmq.controller;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.rabbitmq.entity.File;

@Service
public class RabbitConsumers {

	@RabbitListener(queues = "queue-name1")
	public void getMessagefromQueue1(File file) {
		System.out.println("RabbitConsumers.getMessagefromQueue1()");
		System.out.println(file.getFileType());
	}

	@RabbitListener(queues = "xml")
	public void getMessagefromXml(File file) {
		System.out.println("RabbitConsumers.getMessagefromXml()");
		System.out.println(file.getFileType());
	}

	@RabbitListener(queues = "html")
	public void getMessagefromHtml(File file) {
		System.out.println("RabbitConsumers.getMessagefromHtml()");
		System.out.println(file.getFileType());
	}

	@RabbitListener(queues = "excel")
	public void getMessagefromExcel(File file) {
		System.out.println("RabbitConsumers.getMessagefromExcel()");
		System.out.println(file.getFileType());
	}
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RabbitListener(queues = "header-queue")
	public void getMessageFromHeaderQueue(Message message) throws IOException, ClassNotFoundException {		
		try {
			System.out.println("RabbitConsumers.getMessageFromHeaderQueue()");
            String jsonContent = new String(message.getBody());
            File file = objectMapper.readValue(jsonContent, File.class);
            
            // Process the File object as needed
            System.out.println("Received File: " + file.getFileType());

            // Now you can work with the 'file' object as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
