package com.demo.mq.broker.consumer;

public class MessageNotificationConsumer extends AbstractMessageNotificationConsumer {

	public MessageNotificationConsumer(String id, MessageNotificationBockingQueue queue , MessageNotification stop) {
		super(id, queue, stop);
	}

	@Override
	public void consume(MessageNotification n) {
		if (n == null) {
			//System.out.println("Skipping null message");
			return;
		}
		n.notifySubscriber();
		
	}

}
