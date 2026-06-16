package FullBicho.app.service;


import FullBicho.app.entity.Bet;
import FullBicho.app.entity.RoundDigit;
import FullBicho.app.util.items.DigitPosition;
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
        return calculatePayout(bet, DigitPosition.HEAD);
    }

    public double calculatePayout(Bet bet, DigitPosition position) {
        boolean head = position == DigitPosition.HEAD;

        switch (bet.getType()) {
            case THOUSANDS: return bet.getAmount() * (head ? 4000 : 800);
            case HUNDREDS: return bet.getAmount() * (head ? 600 : 120);
            case TENS: return bet.getAmount() * (head ? 60 : 12);
            case GROUP: return bet.getAmount() * (head ? 18 : 3.6);
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
                        totalPayout += calculatePayout(bet, digit.getPosition());
                    }

                    RoundDigit firstWinner = winners.get(0);
                    bet.markAsWin(firstWinner.getDigit(), firstWinner.getPosition(), totalPayout);
                    walletService.creditValue(bet.getUser(), totalPayout);

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
