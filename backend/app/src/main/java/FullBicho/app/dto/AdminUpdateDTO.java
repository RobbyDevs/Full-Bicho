package FullBicho.app.dto;

import FullBicho.app.util.items.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AdminUpdateDTO extends UserUpdateDTO{



    private RoleType  role;

    private final Double balance = 1000.00;

    public AdminUpdateDTO(String username, String email, String password, String cpf,RoleType role) {
        super(username, email, password, cpf);
        this.role = role;
    }
}