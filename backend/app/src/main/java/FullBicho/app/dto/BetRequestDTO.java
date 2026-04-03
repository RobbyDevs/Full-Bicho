package FullBicho.app.dto;

import FullBicho.app.entity.Draw;
import FullBicho.app.entity.User;
import FullBicho.app.util.BetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BetRequestDTO {

    private Long user_id;
    private Long drawId;

    private User user;
    private Draw draw;

    private BetType type;
    private Long chosenNumber;
    private double amount;

}
