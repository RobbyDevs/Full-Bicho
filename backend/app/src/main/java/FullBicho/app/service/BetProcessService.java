package FullBicho.app.service;


import FullBicho.app.entity.Bet;
import FullBicho.app.entity.RoundDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class BetProcessService {

    @Autowired
    private WalletService walletService;

    public List<RoundDigit> getWinningDigits(Bet bet, List<RoundDigit> digitResults) {

        List<RoundDigit> winners = new ArrayList<>();

        for (RoundDigit digit : digitResults) {
            if (betMatches(bet, digit)) {
                winners.add(digit);
            }
        }

        return winners;
    }


    private Long getGroup(Long number) {
        Long tens = number % 100;
        Long group = (tens - 1) / 4 + 1;
        return group == 0 ? 25 : group;
    }


    private boolean betMatches(Bet bet, RoundDigit digit) {

        Long resultNumber = digit.getDigit();
        Long chosen = bet.getChosenNumber();

        switch (bet.getType()) {

            case THOUSANDS:
                return Objects.equals(resultNumber, chosen);

            case HUNDREDS:
                return resultNumber % 1000 == chosen;

            case TENS :
                return resultNumber % 100 == chosen;

            case GROUP:
                return getGroup(resultNumber).equals(chosen);

            default:
                return false;
        }
    }

    public boolean checkIfBetWins(Bet bet, List<RoundDigit> digitResults) {

        for (RoundDigit digit : digitResults) {

            if (betMatches(bet, digit)) {
                return true;
            }
        }
        return false;
    }

    public double calculatePayout(Bet bet) {

        switch (bet.getType()) {
            case THOUSANDS: return bet.getAmount() * 4000;
            case HUNDREDS: return bet.getAmount() * 600;
            case TENS: return bet.getAmount() * 60;
            case GROUP: return bet.getAmount() * 18;
            default: return 0;
        }
    }



    public void processBets(List<Bet> bets, List<RoundDigit> results) {
        try {

            for (Bet bet : bets) {
                List<RoundDigit> winners = getWinningDigits(bet, results);

                if (!winners.isEmpty()) {
                    double totalPayout = 0.0;

                    for (RoundDigit digit : winners) {
                        double payout = calculatePayout(bet);

                        //Marcando como GANHO
                        bet.markAsWin(digit.getDigit(), digit.getPosition(), payout);

                        totalPayout += payout;
                    }

                    walletService.credit(bet.getUser(), totalPayout);

                } else {
                    bet.markAsLoss();
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}