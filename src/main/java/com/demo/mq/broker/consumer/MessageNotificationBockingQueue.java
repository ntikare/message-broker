package com.demo.mq.broker.consumer;

public interface MessageNotificationBockingQueue {
	
	public void offer(MessageNotification n);
	public MessageNotification peek();
	public MessageNotification waitAndTake();

}
