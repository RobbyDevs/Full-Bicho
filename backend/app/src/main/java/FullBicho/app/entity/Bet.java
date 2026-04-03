package FullBicho.app.entity;

import FullBicho.app.util.BetStatus;
import FullBicho.app.util.BetType;
import FullBicho.app.util.DigitPosition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bet_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drawId", nullable = false)
    private Draw draw;


    @Enumerated(EnumType.STRING)
    private BetType type; //REQUEST BODY

    private Long chosenNumber; //REQUEST BODY
    private double amount; //REQUEST BODY

    @Enumerated(EnumType.STRING)
    private BetStatus status; // PENDING, WIN, LOSE

    private Long resultNumber;

    @Enumerated(EnumType.STRING)
    private DigitPosition digitPosition;

    private double payout;


    //-----------------------
    // MARCAR BET COMO GANHO
    public void markAsWin(Long resultNumber, DigitPosition position, double payout) {
        if (this.status != BetStatus.PENDING) {
            throw new IllegalStateException("BET JÁ FOI PROCESSADA");
        }

        this.status = BetStatus.WIN;
        this.resultNumber = resultNumber;
        this.digitPosition = position;
        this.payout = payout;
    }

    // Marca BET como PERDA
    public void markAsLoss() {
        if (this.status != BetStatus.PENDING) {
            throw new IllegalStateException("Bet already processed");
        }

        this.status = BetStatus.LOSE;
        this.payout = 0.0;
    }

    // MÉTODOS UTEIS-------------
    
    //Verificação de Status

    public boolean isPending() {
        return this.status == BetStatus.PENDING;
    }

    //Verifica a qual Sorteio a bet pertence
    public boolean belongsToDraw(Long drawId) {
        return this.draw.getDrawId().equals(drawId);
    }

    public User getUser() {
        return this.user;
    }

    public Long getChosenNumber(){
        return this.chosenNumber;
    }

    public BetType getType() {
        return this.type;
    }

    public double getAmount() {
        return amount;
    }
}

