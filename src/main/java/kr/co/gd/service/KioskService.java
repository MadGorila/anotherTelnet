package kr.co.gd.service;

import kr.co.gd.Entity.Kiosk;
import kr.co.gd.dto.KioskDTO;

public interface KioskService {
    void bulkInsert();
    Kiosk findByStatus(String status);
    void sendOrderByMqtt(KioskDTO kioskDTO);
    void nextStep(KioskDTO kioskDTO);
}
