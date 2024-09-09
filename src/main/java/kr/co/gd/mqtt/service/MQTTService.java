package kr.co.gd.mqtt.service;

import kr.co.gd.mqtt.dto.MQTTDTO;

public interface MQTTService {
    void publish(MQTTDTO dto);
    boolean connected();
    void reconnect();
}
