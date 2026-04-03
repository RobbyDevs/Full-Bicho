package FullBicho.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class UserRequestDTO {
    private Long user_id;
    private String username;


    private String password;
    private String email;
    private final Double balance = 1000.00;

}
