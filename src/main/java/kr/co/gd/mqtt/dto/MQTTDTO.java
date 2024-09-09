package kr.co.gd.mqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MQTTDTO {
    private String topic;
    private String msgId;
    private String deviceId;
    private List<AddedObject> addedObjects;
    private Event event;
}
