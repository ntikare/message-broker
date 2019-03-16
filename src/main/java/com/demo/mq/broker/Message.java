package com.demo.mq.broker;

public class Message {
	
	private String data;

	public Message(String data) {
		super();
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [data=" + data + "]";
	}

}
