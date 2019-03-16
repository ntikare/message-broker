package com.demo.mq.broker.subscription;

import com.demo.mq.broker.Topic;

public class SubscriptionFactory {
	
	public static Subscription getSubscription(SubscriptionType type, Topic topic) {
		if (type == SubscriptionType.DEMO) {
			return new DemoSubscription(topic);
		}
		if (type == SubscriptionType.EMAIL) {
			return new EmailSubscription(topic);
		}	
		return null;
		
	}

}
