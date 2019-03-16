package com.demo.mq.broker.consumer;

public abstract class AbstractMessageNotificationConsumer extends Thread {
	
	private volatile boolean  STOP_FLAG = false;
	private MessageNotificationBockingQueue queue;
	private String id;
	private final MessageNotification STOP;

	
	public AbstractMessageNotificationConsumer(String id, MessageNotificationBockingQueue queue, MessageNotification stop) {
		this.id = id;
		this.queue = queue;
		this.STOP = stop;
		//this.startConsuemerThread();
		
		this.setDaemon(true);
	}
	
	public abstract void consume(MessageNotification n);
	
	public void run() {
		while(!STOP_FLAG || queue.peek()!= null) {
			//System.out.println("wating");
			MessageNotification notification;
			try {
				notification = queue.waitAndTake();
				if (STOP != notification) {
					//System.out.println("Consuming message by " + this.id);
					
					consume(notification);
				} else {
					//System.out.println("STOP");
					STOP_FLAG = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.id + " stopped");
	}

	public void notifyStop() {
		//System.out.println(this.id + " stopping");
		//queue.offer(STOP);
		
	}
	
}
