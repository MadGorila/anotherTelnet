package kr.co.gd.mqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class Event {
    @Builder.Default
    private String objectPath = "Device.Order.";
    @Builder.Default
    private String eventId = "StateChanged";
    private List<Parameters> parameters;
}
