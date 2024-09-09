package kr.co.gd.mqtt.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.gd.mqtt.dto.MQTTDTO;
import kr.co.gd.mqtt.service.MQTTService;
import lombok.extern.java.Log;
import org.eclipse.paho.mqttv5.client.IMqttClient;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Log
@Service
public class MQTTServiceImpl implements MQTTService {

    // TODO
    @Autowired
    private IMqttClient mqttClient;

    @Override
    public void publish(MQTTDTO dto) {
        Gson gson = new Gson();
        String msgId = gson.toJson("554ac5bb-2908-4aea-a871-fcc8ad0b5cf3");
        String deviceId = gson.toJson(dto.getDeviceId());
        String addObjects = gson.toJson(dto.getAddedObjects());
        String event = gson.toJson(dto.getEvent());

        log.info("addObjects: " + addObjects);
        log.info("event: " + event);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("messageId", msgId);
        jsonObject.addProperty("deviceId", deviceId);
        log.info(">>>>> " + ObjectUtils.isEmpty(addObjects));
        log.info(">>>>> " + addObjects.isEmpty());
        if (!"null".equals(addObjects)) {
            jsonObject.addProperty("addedObjects", addObjects);
        }
        log.info(">>>>> " + ObjectUtils.isEmpty(event));
        log.info(">>>>> " + event.isEmpty());
        if (!"null".equals(event)) {
            jsonObject.addProperty("event", event);
        }
        String payload = gson.toJson(jsonObject);

        payload = payload.replace("\\", "");
        payload = payload.replace("\"\"", "\"");
        payload = payload.replace("\"[", "[");
        payload = payload.replace("]\"", "]");
        payload = payload.replace("\"{", "{");
        payload = payload.replace("}\"", "}");
//        payload = payload.replace("\"\\\"", "\"");
//        payload = payload.replace("\\\"", "");
//        payload = payload.replaceAll("\\\"", "\"");

        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(2);
        mqttMessage.setRetained(false);

        try {
            log.info(">>>>> why 2?");
            mqttClient.publish(dto.getTopic(), mqttMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean connected() {
        return mqttClient.isConnected();
    }

    @Override
    public void reconnect() {
        try {
            log.info("trying to reconnect mqtt server.");
            mqttClient.reconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
