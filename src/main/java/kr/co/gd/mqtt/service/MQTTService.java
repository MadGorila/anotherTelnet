package kr.co.gd.mqtt.service;

import kr.co.gd.mqtt.dto.MQTTDto;

public interface MQTTService {
    void publish(MQTTDto dto);
    boolean connected();
    void reconnect();
}
