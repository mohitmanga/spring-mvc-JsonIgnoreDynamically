package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Message;
import com.example.service.IMessageService;

/**
 * Message Controller
 */
@RestController
@RequestMapping(value = "/rs")
public class MessageController {

	@Autowired
	private IMessageService iMessageService;

	@RequestMapping(value = "/message/{src}", produces = "application/json", method = RequestMethod.GET)
	public Message getMessage(@PathVariable("src") String soruce) {
		return iMessageService.getMessage(0);
	}
}
