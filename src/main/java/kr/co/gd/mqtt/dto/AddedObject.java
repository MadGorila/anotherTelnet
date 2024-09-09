package kr.co.gd.mqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class AddedObject {
    @Builder.Default
    private String objectPath = "Device.Order.";
    private List<Properties> properties;
}
