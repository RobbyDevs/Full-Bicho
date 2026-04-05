package FullBicho.app.entity;


import FullBicho.app.util.items.DigitPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class RoundDigit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundDigitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drawId",nullable = false)
    private Draw draw;

    private Long digit;

    @Enumerated(EnumType.STRING)
    private DigitPosition position;


    public void generateDigit(){
        this.digit =  ThreadLocalRandom.current().nextLong(0,10000);
    }
    public void setDraw(Draw draw){
        this.draw = draw;
    }
    public void setPosition(DigitPosition position){
        this.position = position;
    }

    public Long getDigit(){
        return this.digit;
    }
}
