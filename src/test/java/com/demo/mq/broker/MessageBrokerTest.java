package com.demo.mq.broker;

import org.junit.jupiter.api.Test;

class MessageBrokerTest {

	@Test
	void threadSaefty() throws InterruptedException {
		MessageBroker broker = new InMemoryMessageBroker();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Creating one" + i);

					Topic one = broker.createTopic("one" + i);
					//System.out.println(broker.listTopics());
					try {
						Thread.sleep(100);
						broker.removeTopic(one);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < 10; i++) {
					System.out.println("Creating two" + i);
					Topic two = broker.createTopic("two" + i);
					
					try {
						Thread.sleep(100);
						broker.removeTopic(two);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < 30; i++) {
					System.out.println(broker.listTopics());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
		
		t3.start();
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		t3.join();

	}

}
