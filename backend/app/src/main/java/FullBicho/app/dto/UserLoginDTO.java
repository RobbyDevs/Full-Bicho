package FullBicho.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginDTO {

    @NotBlank(message = "EMAIL OBRIGATÓRIO")
    private String email;
    @NotBlank(message = "SENHA OBRIGATÓRIO")
    private String password;

}
