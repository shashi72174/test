package fanoutexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PublishMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish("fanoutExchange", "fanout1", null, "this is fanout message".getBytes());
            //channel.basicPublish("fanoutExchange", "fanout2", null, "this is fanout message".getBytes());
            //channel.basicPublish("fanoutExchange", "fanout3", null, "this is fanout message".getBytes());
            channel.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
