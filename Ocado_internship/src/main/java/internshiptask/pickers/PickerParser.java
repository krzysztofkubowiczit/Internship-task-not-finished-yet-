package internshiptask.pickers;

import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PickerParser {

    public void parsePicker(List<Picker> pickerList) throws Exception {

        Path inputPath = Paths.get("C:\\Users\\kkubo\\IdeaProjects\\OcadoInternShipTask\\src\\main\\java\\internshiptask\\store.json");
        String source = Files.readString(inputPath, Charset.forName("UTF-8"));
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(source);

            for (JsonNode pickerNode : node.get("pickers")) {
                String pickerId = pickerNode.asText();
                String pickingStartTimeStr = node.get("pickingStartTime").asText();
                LocalTime pickingStartTime = LocalTime.parse(pickingStartTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                String pickingEndTimeStr = node.get("pickingEndTime").asText();
                LocalTime pickingEndTime = LocalTime.parse(pickingEndTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                Picker picker = new Picker();
                picker.setPickerId(pickerId);
                picker.setPickingStart(pickingStartTime);
                picker.setPickingEnd(pickingEndTime);
                pickerList.add(picker);
            }


        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
