package directexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

public class ConsumeMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicConsume("queueTest", true, (ConsumeTag, deliverMessage) -> {
                String retrievedMessage = new String(deliverMessage.getBody());
                JSONObject jsonObject = new JSONObject(retrievedMessage);
                System.out.println(jsonObject.get("name")+"\t"+jsonObject.get("age")+"\t"+jsonObject.get("salary"));
            }, (consumeTag) -> {});
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
