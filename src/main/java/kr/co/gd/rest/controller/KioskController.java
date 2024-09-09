package kr.co.gd.rest.controller;

import kr.co.gd.dto.KioskDTO;
import kr.co.gd.service.KioskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class KioskController {

    @Autowired
    private KioskService kioskService;

    @GetMapping("/send")
    public ResponseEntity<Void> startOrder(KioskDTO req) {
        kioskService.sendOrderByMqtt(req);
        kioskService.nextStep(req);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
