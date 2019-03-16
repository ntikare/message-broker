package com.demo.mq.broker.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageNotificationBockingQueueImpl implements MessageNotificationBockingQueue {
	
	
	private BlockingQueue<MessageNotification> blockingQueue = new LinkedBlockingQueue<>();


	@Override
	public void offer(MessageNotification n) {
		blockingQueue.add(n);		
	}

	@Override
	public MessageNotification peek() {
		// TODO Auto-generated method stub
		return blockingQueue.peek();
	}

	@Override
	public MessageNotification waitAndTake() {
		// TODO Auto-generated method stub
		try {
			return blockingQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
