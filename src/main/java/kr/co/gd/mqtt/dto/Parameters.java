package kr.co.gd.mqtt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Parameters {
    private String parameterId;
    private String value;
    @Builder.Default
    private String valueType = "string";
}
