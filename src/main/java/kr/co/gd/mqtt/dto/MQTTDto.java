package kr.co.gd.mqtt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MQTTDto {
    private String topic;
    private String msgId;
    private String deviceId;
    private List<AddObjects> addedObjects;
    private Event event;

    @Getter
    @Setter
    public class AddObjects {
        private String objectPath;
        private List<Properties> properties;
    }

    @Getter
    @Setter
    public class Event {
        private String objectPath;
        private String eventId;
        private List<Properties> properties;
    }

    @Getter
    @Setter
    public class Properties {
        private String propertyId;
        private String value;
        private String valueType;
    }
}
