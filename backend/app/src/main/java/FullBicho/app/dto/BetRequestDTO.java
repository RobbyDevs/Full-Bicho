package FullBicho.app.dto;

import FullBicho.app.util.items.BetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BetRequestDTO {

    private Long userId;
    private Long drawId;

    //private User user;
    //private Draw draw;

    private BetType type;
    private Long chosenNumber;
    private double amount;

}
