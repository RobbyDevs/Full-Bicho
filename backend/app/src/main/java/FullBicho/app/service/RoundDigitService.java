package FullBicho.app.service;


import FullBicho.app.util.items.DigitPosition;
import FullBicho.app.entity.Draw;
import FullBicho.app.entity.RoundDigit;
import FullBicho.app.repository.RoundDigitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RoundDigitService {

    @Autowired
    private RoundDigitRepository roundDigitRepository;



    @Transactional
    public boolean generateForDraw(Draw draw) {

        try{

            List<DigitPosition> positions = List.of(
                    DigitPosition.HEAD,
                    DigitPosition.SECOND,
                    DigitPosition.THIRD,
                    DigitPosition.FOURTH,
                    DigitPosition.FIFTH
            );

            for (DigitPosition position : positions) {
                RoundDigit roundDigit = new RoundDigit();
                roundDigit.generateDigit();
                roundDigit.setDraw(draw);

                roundDigit.setPosition(position);
                roundDigitRepository.save(roundDigit);
            }


            return true;
        }
        catch (Exception e){

            throw new RuntimeException(e.getMessage());
        }
    }
}