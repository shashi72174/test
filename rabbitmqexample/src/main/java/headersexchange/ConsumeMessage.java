package headersexchange;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class ConsumeMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();


            /*Map<String,Object> map2 = new HashMap<>();
            map2.put("item2", "mobile");
            map2.put("item1", "mob");*/



            channel.basicConsume("header3", true, ((s, delivery) -> {
                System.out.println(new String(delivery.getBody()));
            }),(consumeTag) -> {});

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
