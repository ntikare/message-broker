package com.demo.mq.broker.subscription;

import com.demo.mq.broker.Message;
import com.demo.mq.broker.Topic;

public class EmailSubscription extends Subscription {

	public EmailSubscription(Topic topic) {
		super(topic);
	}

	@Override
	public void consume(Message message) {
		//System.out.println("[Email] " +  this.getDisplayName() + " " + message);
	}
}

