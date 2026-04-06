package FullBicho.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

@Setter
@Getter
public class UserUpdateDTO {

    private String username;
    private String email;

    @NotBlank(message = "SENHA OBRIGATÓRIA!!!")
    private String password;
    @NotBlank(message = "CPF INVÁLIDO!!!")
    //@CPF (message = "CPF INVÁLIDO!!")
    private String cpf;

    private final Double balance = 1000.00;

}