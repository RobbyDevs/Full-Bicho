package FullBicho.app.repository;

import FullBicho.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> cpf(String cpf);

    boolean existsByCpf(String cpf);
    void deleteByCpf(String cpf);
}
