package headersexchange;

import com.rabbitmq.client.*;

import java.util.HashMap;
import java.util.Map;

public class PublishMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            Map<String,Object> map = new HashMap<>();
            map.put("item1", "mobile");
            map.put("item2", "television");
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
            basicProperties = basicProperties.builder().headers(map).build();


            Map<String,Object> map1 = new HashMap<>();
            map1.put("item1", "mobile");
            map1.put("item2", "ac");

            AMQP.BasicProperties basicProperties1 = new AMQP.BasicProperties();
            basicProperties1 = basicProperties1.builder().headers(map1).build();


            Map<String,Object> map2 = new HashMap<>();
            map2.put("item2", "television");
            map2.put("item1", "ac");

            AMQP.BasicProperties basicProperties2 = new AMQP.BasicProperties();
            basicProperties2 = basicProperties2.builder().headers(map2).build();

            channel.basicPublish("headerExchange", "", basicProperties, "this is header message".getBytes());
            channel.basicPublish("headerExchange", "", basicProperties1, "this is header message".getBytes());
            channel.basicPublish("headerExchange", "", basicProperties2, "this is header message".getBytes());
            channel.close();
            connection.close();

        }catch (Exception e) {
            e.printStackTrace();
        }



    }

}
