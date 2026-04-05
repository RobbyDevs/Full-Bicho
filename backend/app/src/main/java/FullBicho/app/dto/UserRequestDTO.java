package FullBicho.app.dto;

import FullBicho.app.util.items.InputTreatment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor

@Setter
@Getter
public class UserRequestDTO {

    @NotBlank(message = "NOME DE USUÁRIO OBRIGATÓRIO!!!")
    private String username;
    @NotBlank(message = "SENHA OBRIGATÓRIA!!!")
    private String password;
    @NotBlank(message = "EMAIL OBRIGATÓRIO!!!")
    @Email(message = "EMAIL INVÁLIDO")
    private String email;
    @NotBlank(message = "CPF INVÁLIDO!!!")
    //@CPF (message = "CPF INVÁLIDO!!")
    private String cpf;

    private final Double balance = 1000.00;

}