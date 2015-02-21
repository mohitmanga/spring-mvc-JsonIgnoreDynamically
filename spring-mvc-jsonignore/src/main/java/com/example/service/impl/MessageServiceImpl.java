package com.example.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.Message;
import com.example.dto.User;
import com.example.service.IMessageService;

/**
 * Message service
 */
@Service
public class MessageServiceImpl implements IMessageService {

	/* (non-Javadoc)
	 * @see com.example.service.IMessageService#getMessage(long)
	 */
	public Message getMessage(long messageId) {
		Message message = new Message();
		User user = new User();
		user.setAddress("Address 1");
		user.setCity("City One");
		user.setCountry("India");
		user.setEmail("test@test.com");
		user.setFirstname("Fname");
		user.setId(100l);
		user.setLastname("Lname");
		user.setPostalCode("11111");
		message.setAuthor(user);
		message.setBody("Body");
		message.setCreated(new Date());
		message.setId(1l);
		List<User> recipients = new ArrayList<User>();
		recipients.add(user);
		recipients.add(user);
		message.setRecipients(recipients);
		message.setTitle("Message One");
		return message;
	}

}
