package exchangetoexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PublishMessageToExchange {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish("exchangeTest", "fanoutExchange", null, "exchange to exchange".getBytes());
            channel.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
