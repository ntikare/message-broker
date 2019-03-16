package com.demo.mq.broker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.demo.mq.broker.consumer.MessageConsumerPool;
import com.demo.mq.broker.consumer.MessageNotificationBockingQueue;
import com.demo.mq.broker.consumer.MessageNotificationBockingQueueImpl;
import com.demo.mq.broker.subscription.Subscription;
import com.demo.mq.broker.subscription.SubscriptionFactory;
import com.demo.mq.broker.subscription.SubscriptionType;

public class InMemoryMessageBroker implements MessageBroker {
	
	private Map<String, Topic> topicsMap = new ConcurrentHashMap<>();
	private MessageConsumerPool consumerPool;
	private MessageNotificationBockingQueue queue;
	
	public InMemoryMessageBroker() {
		queue = new MessageNotificationBockingQueueImpl();
		consumerPool = new MessageConsumerPool(5, queue);
	}

	@Override
	public Topic createTopic(String name) {
		Topic topic = new Topic(name, queue);
		topicsMap.put(topic.getId(), topic);
		return topic;
	}
	
	public void addConsumers(int newCapacity) {
		consumerPool.addNewConsumers(newCapacity);
	}

	@Override
	public void removeTopic(Topic topic) {
		topicsMap.remove(topic.getId());
	}

	@Override
	public Subscription createSubscription(Topic topic, SubscriptionType type) {
		Subscription sub = SubscriptionFactory.getSubscription(type, topic);
		topic.subscribe(sub);
		return sub;
	}

	@Override
	public void removeSubscription(Subscription sub) {
		sub.getSubscribedTopic().unSubscribe(sub);
	}
	
	@Override
	public List<Topic> listTopics() {
		return new ArrayList<>(topicsMap.values());
	}

}
