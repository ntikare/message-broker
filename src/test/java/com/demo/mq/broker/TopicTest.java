package com.demo.mq.broker;

import com.demo.mq.broker.subscription.Subscription;
import com.demo.mq.broker.subscription.SubscriptionType;

class TopicTest {

	public static void main(String args[]) {
		try {
			test();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void  test() throws InterruptedException {
		//fail("Not yet implemented");
		
		MessageBroker broker = new InMemoryMessageBroker();
		Topic one = broker.createTopic("one");
		Subscription sub2 = broker.createSubscription(one, SubscriptionType.DEMO);

		Subscription sub3 = broker.createSubscription(one, SubscriptionType.EMAIL);
		
		Topic two = broker.createTopic("two");
		Subscription sub4 = broker.createSubscription(two, SubscriptionType.EMAIL);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						one.publish(new Message("one" + i));
						Thread.sleep(100);

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
					try {
						two.publish(new Message("two" + i));
						Thread.sleep(100);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
