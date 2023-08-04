package internshiptask.orders;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderParser {

    public void parseOrder(List<Order> orderList) throws Exception {

        Path inputPath = Paths.get("C:\\Users\\kkubo\\IdeaProjects\\OcadoInternShipTask\\src\\main\\java\\internshiptask\\orders.json");
        List<String> source = Files.readAllLines(inputPath, Charset.forName("UTF-8"));
        ObjectMapper mapper = new ObjectMapper();

        try {

            for (int i = 0; i < source.size(); i++) {
                if (source.get(i).startsWith("    \"orderId\"")) {
                    JsonNode node = mapper.readTree(source.get(i - 1) + source.get(i) + source.get(i + 1) + source.get(i + 2) + source.get(i + 3) + source.get(i + 4));
                    String orderId = node.get("orderId").asText();
                    BigDecimal orderValue = new BigDecimal(node.get("orderValue").asText());
                    String completeByStr = node.get("completeBy").asText();
                    LocalTime completeBy = LocalTime.parse(completeByStr, DateTimeFormatter.ofPattern("HH:mm"));
                    String pickingTimeStr = node.get("pickingTime").asText();
                    Duration pickingTime = Duration.parse(pickingTimeStr);

                    Order order = new Order();
                    order.setOrderId(orderId);
                    order.setOrderValue(orderValue);
                    order.setCompleteBy(completeBy);
                    order.setPickingTime(pickingTime);
                    orderList.add(order);
                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
