package FullBicho.app.service;

import FullBicho.app.dto.BetRequestDTO;
import FullBicho.app.entity.*;
import FullBicho.app.repository.BetRepository;
import FullBicho.app.repository.DrawRepository;
import FullBicho.app.repository.UserRepository;
import FullBicho.app.util.items.BetStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //Verificar se usuario existe
        User user = userRepository.findById(betRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("USUÁRIO NÃO ENCONTRADO!!!"));

        //Verificar se o draw existe
        Draw draw = drawRepository.findById(betRequest.getDrawId())
                .orElseThrow(() -> new RuntimeException("SORTEIO NÃO ENCONTRADO!!!"));

        //verificar se o draw está aberto
        if (!draw.isOpen()) {
            throw new IllegalStateException("SORTEIO ENCERRADO!!!");
        }

        // debitar valor da aposta;
        try {
            walletService.debitValue(user, betRequest.getAmount());
        }
        catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

        //SALVAR APOSTA
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

    // COLOCA VÁRIAS APOSTAS - PARA TESTES/DEBUG SOMENTE
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
            //debitValue()
            betRepository.save(bet);
        }

        return "Bets Criadas com Sucesso!";
    }


}

