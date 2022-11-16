package com.saeed.events.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saeed.events.models.Message;
import com.saeed.events.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepo;
	
	public List<Message> allMessages(){
		return messageRepo.findAll();
	}
	public Message createMessage(Message m) {
		return messageRepo.save(m);
	}
}
