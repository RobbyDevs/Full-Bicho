package FullBicho.app.util.items;


import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class InputTreatment {

    public void checkBlank(String input) {
        if (input.isBlank() || input == null) {
            throw new IllegalArgumentException("CAMPO OBRIGATÓRIO EM BRANCO!");
        }
    }


    public String treatCPF(String cpf) {

        String newCPF =  cpf.replaceAll("\\D", "");
        if (newCPF.length() != 11) {
            throw new IllegalArgumentException("CPF INVÁLIDO!!!");
        }
        return newCPF;
    }
}
