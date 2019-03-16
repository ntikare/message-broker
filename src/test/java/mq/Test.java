package mq;

import com.demo.mq.broker.InMemoryMessageBroker;
import com.demo.mq.broker.Message;
import com.demo.mq.broker.MessageBroker;
import com.demo.mq.broker.Topic;
import com.demo.mq.broker.subscription.Subscription;
import com.demo.mq.broker.subscription.SubscriptionType;

public class Test {

	public static void main(String[] args) {
		
		MessageBroker broker = new InMemoryMessageBroker();
		broker.listTopics();
		
		Topic one = broker.createTopic("one");
		Topic two = broker.createTopic("two");
		
		one.publish(new Message("message topic 1"));
		
		two.publish(new Message("message topic 2"));

		
		Subscription sub1 = broker.createSubscription(one, SubscriptionType.DEMO);
		
		Subscription sub2 = broker.createSubscription(two, SubscriptionType.DEMO);

		Subscription sub3 = broker.createSubscription(two, SubscriptionType.EMAIL);
		
		
		one.publish(new Message("message topic 11"));
		two.publish(new Message("message topic 22"));
		
		broker.removeSubscription(sub2);
		
		
		one.publish(new Message("message topic 111"));
		two.publish(new Message("message topic 221"));

		

	}

}
