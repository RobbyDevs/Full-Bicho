package FullBicho.app.dto;

import FullBicho.app.util.items.RoleType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.Message;

@Getter
@Setter
public class AdminRequestDTO extends  UserRequestDTO {

    private final RoleType roleType;
    public AdminRequestDTO(String username, String password, String email, String cpf, RoleType roleType, RoleType role) {
        super(username, password, email, cpf);
        this.roleType = role;
    }
}
