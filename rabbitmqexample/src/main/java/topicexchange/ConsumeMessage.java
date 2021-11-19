package topicexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConsumeMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicConsume("topic1", true, ((s, delivery) -> {
                System.out.println(new String(delivery.getBody()));
            }),(consumeTag) -> {});

            channel.basicConsume("topic2", true, ((s, delivery) -> {
                System.out.println(new String(delivery.getBody()));
            }),(consumeTag) -> {});

            channel.basicConsume("topic3", true, ((s, delivery) -> {
                System.out.println(new String(delivery.getBody()));
            }),(consumeTag) -> {});

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
