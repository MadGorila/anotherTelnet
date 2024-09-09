package kr.co.gd.repository;

import kr.co.gd.Entity.Kiosk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KioskRepository extends JpaRepository<Kiosk, Long> {
    Kiosk findByStatus(String status);
}
