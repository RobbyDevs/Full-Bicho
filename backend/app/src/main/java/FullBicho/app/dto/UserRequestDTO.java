package FullBicho.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class UserRequestDTO {
    private Long userId;//AutoIncrement

    private String username;//RequestBody
    private String password;//RequestBody
    private String email;//RequestBody

    private final Double balance = 1000.00;//Default

}
