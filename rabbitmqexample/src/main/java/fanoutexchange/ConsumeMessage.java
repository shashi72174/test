package fanoutexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConsumeMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicConsume("fanout1", true, (consumeTag,deliverMessage) -> System.out.println(new String(deliverMessage.getBody())), (consumeTag) -> {});
            channel.basicConsume("fanout2", true, (consumeTag,deliverMessage) -> System.out.println(new String(deliverMessage.getBody())), (consumeTag) -> {});
            channel.basicConsume("fanout3", true, (consumeTag,deliverMessage) -> System.out.println(new String(deliverMessage.getBody())), (consumeTag) -> {});
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
