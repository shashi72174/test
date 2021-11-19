package directexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

public class PublishMessage {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "raajki");
            jsonObject.put("age", 31);
            jsonObject.put("salary", 150000.00d);
            channel.basicPublish("", "testQueue", null, jsonObject.toString().getBytes());
            channel.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
