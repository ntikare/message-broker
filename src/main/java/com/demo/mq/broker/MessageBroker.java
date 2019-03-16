package com.demo.mq.broker;

import java.util.List;

import com.demo.mq.broker.subscription.Subscription;
import com.demo.mq.broker.subscription.SubscriptionType;

public interface MessageBroker {
	
	public Topic createTopic(String name);
	
	public void removeTopic(Topic topicId);
	
	public Subscription createSubscription(Topic topic, SubscriptionType type);

	public void removeSubscription(Subscription sub);

	public List<Topic> listTopics();

}
