package FullBicho.app.repository;

import FullBicho.app.entity.Bet;
import FullBicho.app.util.items.BetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findByDraw_drawIdAndStatus(Long drawId, BetStatus status);
}
