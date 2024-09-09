package kr.co.gd.mqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Properties {
    private String propertyId;
    private String value;
    private String valueType;
}
