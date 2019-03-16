package com.demo.mq.broker.consumer;

import java.util.ArrayList;
import java.util.List;

public class MessageConsumerPool {
	
	private List<AbstractMessageNotificationConsumer> consumers = new ArrayList<>();
	private MessageNotificationBockingQueue queue;
	private final MessageNotification STOP = new MessageNotification(null, null);

	
	public MessageConsumerPool(int initialCapacity, MessageNotificationBockingQueue queue) {
		this.queue = queue;
		for(int i =0; i<initialCapacity; i++) {
			AbstractMessageNotificationConsumer consumer = new MessageNotificationConsumer("Consuemer-"+ i, queue, STOP);
			consumer.start();
			consumers.add(consumer);
		}
		this.handleShutdown();
		
	}
	
	public void addNewConsumers(int c) {
		for(int i =0; i<c; i++) {
			AbstractMessageNotificationConsumer consumer = new MessageNotificationConsumer("Consuemer-"+ i, queue, STOP);
			consumer.start();
			consumers.add(consumer);
		}
	}
	
	private void handleShutdown() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("Shutdown hook called");
				try {
					for(AbstractMessageNotificationConsumer consumer: consumers) {
						consumer.notifyStop();
						queue.offer(STOP);
					}
					for(AbstractMessageNotificationConsumer consumer: consumers) {
						//consumer.join();
					}
					//join();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
	}

}
