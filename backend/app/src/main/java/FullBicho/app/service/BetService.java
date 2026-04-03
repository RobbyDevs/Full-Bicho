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

import java.awt.desktop.SystemSleepEvent;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class BetService {

    @Autowired
    private BetRepository betRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DrawRepository drawRepository;
    @Autowired
    private WalletService walletService;


    @Transactional
    public String placeBet(BetRequestDTO betRequest) {

        User user = userRepository.findById(betRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Draw draw = drawRepository.findById(betRequest.getDrawId())
                .orElseThrow(() -> new RuntimeException("Draw not found"));

        if (!draw.isOpen()) {
            throw new IllegalStateException("Draw is closed");
        }

        try {
            walletService.debit(user, betRequest.getAmount());
        }
        catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
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

    @Transactional
    public String autoPlaceBet(BetRequestDTO betRequest) {

        User user = userRepository.findById(betRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Draw draw = drawRepository.findById(betRequest.getDrawId())
                .orElseThrow(() -> new RuntimeException("Draw not found"));

        if (!draw.isOpen()) {
            throw new IllegalStateException("Draw is closed");
        }

        int nBets = 10;
        for (int i=0;i<nBets;i++){
        Bet bet = new Bet();

        bet.setUser(user);
        bet.setDraw(draw);
        bet.setType(betRequest.getType());
        bet.setAmount(betRequest.getAmount());
        bet.setStatus(BetStatus.PENDING);
            Long tempChosenDigit =  ThreadLocalRandom.current().nextLong(1,11);
            tempChosenDigit*=10;

            bet.setChosenNumber(tempChosenDigit);
            betRepository.save(bet);
        }

        return "Bets Criadas com Sucesso!";
    }


}

