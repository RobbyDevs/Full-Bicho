package FullBicho.app.service;

import FullBicho.app.dto.BetRequestDTO;
import FullBicho.app.entity.*;
import FullBicho.app.repository.BetRepository;
import FullBicho.app.repository.DrawRepository;
import FullBicho.app.repository.UserRepository;
import FullBicho.app.util.BetStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BetService {

    @Autowired
    private BetRepository betRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DrawRepository drawRepository;


    @Transactional
    public String placeBet(BetRequestDTO betRequest) {

        User user = userRepository.findById(betRequest.getUser().getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Draw draw = drawRepository.findById(betRequest.getDraw().getDrawId())
                .orElseThrow(() -> new RuntimeException("Draw not found"));

        if (!draw.isOpen()) {
            throw new IllegalStateException("Draw is closed");
        }

        Bet bet = new Bet();

        bet.setUser(user);
        bet.setDraw(draw);
        bet.setType(betRequest.getType());
        bet.setChosenNumber(betRequest.getChosenNumber());
        bet.setAmount(betRequest.getAmount());
        bet.setStatus(BetStatus.PENDING);

        betRepository.save(bet);
        return "Bet Criada com Sucesso!";
    }


}

