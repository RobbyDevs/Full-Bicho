package FullBicho.app.repository;

import FullBicho.app.entity.RoundDigit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundDigitRepository extends JpaRepository<RoundDigit,Long> {
    List<RoundDigit> findByDraw_drawId(Long drawId);


}
