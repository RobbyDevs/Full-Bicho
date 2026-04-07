package FullBicho.app.repository;

import FullBicho.app.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> cpf(String cpf);

    boolean existsByCpf(String cpf);
    void deleteByCpf(String cpf);

    User findByCpf(@NotBlank(message = "CPF INVÁLIDO!!!") String cpf);

    User findByEmail(String email);

}
