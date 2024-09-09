package kr.co.gd.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KioskDTO {
    private String topic;
    private String deviceId;
    private String orderId;
    private String orderType;
    private String dropOffPoiId;
    private String status;
}
