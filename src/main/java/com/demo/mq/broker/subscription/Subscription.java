package com.demo.mq.broker.subscription;

import org.apache.commons.lang.RandomStringUtils;

import com.demo.mq.broker.Message;
import com.demo.mq.broker.Topic;

public abstract class Subscription {
	
	private Topic subscribedTopic;
	private String id;
	
	public Subscription(Topic topic) {
		this.subscribedTopic = topic;
		this.id =  RandomStringUtils.randomAlphanumeric(8); // TODO UUID
		
	}
	
	public Topic getSubscribedTopic() {
		return subscribedTopic;
	}

	public String getId() {
		return id;
	}
	
	public void notifyMessage(Message message) {
		this.consume(message);
		
	}
	
	public String getDisplayName() {
		return subscribedTopic.getName() + "-" + id;
	}

	public abstract void consume(Message message);

}
