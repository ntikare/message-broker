package com.demo.mq.broker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.RandomStringUtils;

import com.demo.mq.broker.consumer.MessageNotification;
import com.demo.mq.broker.consumer.MessageNotificationBockingQueue;
import com.demo.mq.broker.subscription.Subscription;

public class Topic {
	
	private String name;
	private String id;
	private Map<String, Subscription> subscriptionsMap = new ConcurrentHashMap<>();
	private MessageNotificationBockingQueue queue;
	
	public Topic(String name, MessageNotificationBockingQueue queue) {
		super();
		this.name = name;
		this.queue = queue;
		this.id = RandomStringUtils.randomAlphanumeric(8); //TODO generate UUID
		
	}
	
	public void publish(Message message) {
		//System.out.println("[Publish] " + name + " " + message);
		
		for(Subscription sub: subscriptionsMap.values()) {
			MessageNotification not = new MessageNotification(message, sub);
			queue.offer(not);
		}
	}
	
	
	public void subscribe(Subscription subscription) {
		subscriptionsMap.put(subscription.getId(), subscription);
	}
	
	public void unSubscribe(Subscription subscription) {
		subscriptionsMap.remove(subscription.getId());
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Topic [name=" + name + ", id=" + id;
	}
}
