package com.demo.mq.broker.subscription;

import com.demo.mq.broker.Message;
import com.demo.mq.broker.Topic;

public class DemoSubscription extends Subscription {

	public DemoSubscription(Topic topic) {
		super(topic);
	}

	@Override
	public void consume(Message message) {
		//System.out.println("[Demo] " +  this.getDisplayName() + " " + message);

	}

}