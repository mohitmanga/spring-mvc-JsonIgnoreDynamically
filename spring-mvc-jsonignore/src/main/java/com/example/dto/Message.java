package com.example.dto;

import java.util.Date;
import java.util.List;

import com.example.annotation.IgnoreFields;
import com.example.annotation.SourceFields;
import com.example.common.Platform;

/**
 * Provide information regarding an Message
 *
 */
@IgnoreFields(sourceFields = {
		@SourceFields(fields = { "created" }, platform = Platform.ANDROID),
		@SourceFields(fields = { "body", "id" }, platform = Platform.IOS) })
public class Message {
	private Long id;
	private Date created;
	private String title;
	private User author;
	private List<User> recipients;
	private String body;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<User> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<User> recipients) {
		this.recipients = recipients;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
