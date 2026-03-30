package org.ac.cst8277.Joyce.Samuel.feed_service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Post {
	private int post_id;
	private int producerId;
	private String content;
	private Timestamp raw_time;
	private LocalDateTime timestamp;

	public Post() {}
	
	public Post(Integer producer_id, String content) {
		this.producerId = producer_id;
		this.content = content;
		raw_time = new Timestamp(System.currentTimeMillis());
		timestamp = raw_time.toLocalDateTime();
	}
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int message_id) {
		this.post_id = message_id;
	}

	public int getProducer_id() {
		return producerId;
	}

	public void setProducer_id(int producer_id) {
		this.producerId = producer_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRaw_time() {
		return raw_time;
	}

	public void setRaw_time(Timestamp raw_time) {
		this.raw_time = raw_time;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
		if (timestamp != null) {
			this.raw_time = Timestamp.valueOf(timestamp);
		}
	}
}
