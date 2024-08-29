package kr.co.gd.rest.controller;

import kr.co.gd.mqtt.dto.MQTTDto;
import kr.co.gd.mqtt.service.MQTTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
public class MQTTController {

    @Autowired
    private MQTTService mqttService;

    @PostMapping("/pub")
    public ResponseEntity<Void> publish(@RequestBody MQTTDto dto) {
        mqttService.publish(dto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
