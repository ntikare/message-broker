package com.demo.mq.broker.consumer;

import com.demo.mq.broker.Message;
import com.demo.mq.broker.subscription.Subscription;

public class MessageNotification {
	
	private Message message;
	private Subscription notifyTo;
	
	
	public MessageNotification(Message message, Subscription notifyTo) {
		super();
		this.message = message;
		this.notifyTo = notifyTo;
	}
	public Message getMessage() {
		return message;
	}
	public Subscription getNotifyTo() {
		return notifyTo;
	}
	
	public void notifySubscriber() {
		if (notifyTo == null) {
//			System.out.println("Skipping null subscriber");
			return;
		}
		notifyTo.notifyMessage(message);
	}


}
