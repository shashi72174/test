package topicexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PublishMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection  = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
                //channel.basicPublish("topicExchange", "*.topic2.*", null, "this is topic message".getBytes());
            //channel.basicPublish("topicExchange", "topic1.*", null, "this is topic message".getBytes());
            channel.basicPublish("topicExchange", "topic1.#", null, "this is topic1.# message".getBytes());
            //channel.basicPublish("topicExchange", "#.topic2.#", null, "this is topic1.# message".getBytes());
            //channel.basicPublish("topicExchange", "*.*.topic3", null, "this is topic1.# message".getBytes());
            channel.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
