package kr.co.gd.mqtt.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.gd.mqtt.dto.MQTTDto;
import kr.co.gd.mqtt.service.MQTTService;
import lombok.extern.java.Log;
import org.eclipse.paho.mqttv5.client.IMqttClient;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class MQTTServiceImpl implements MQTTService {

    // TODO
    @Autowired
    private IMqttClient mqttClient;

    @Override
    public void publish(MQTTDto dto) {
        Gson gson = new Gson();
        String msgId = gson.toJson(dto.getMsgId());
        String deviceId = gson.toJson(dto.getDeviceId());
        String addObjects = gson.toJson(dto.getAddedObjects());
        String event = gson.toJson(dto.getEvent());


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msgId", msgId);
        jsonObject.addProperty("deviceId", deviceId);
        jsonObject.addProperty("addedObjects", addObjects);
        jsonObject.addProperty("event", event);
        String payload = gson.toJson(jsonObject);

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
