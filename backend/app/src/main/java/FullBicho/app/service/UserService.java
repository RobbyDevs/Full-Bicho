package FullBicho.app.service;

import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.entity.User;
import FullBicho.app.repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository tempRepository;

    // CREATE
    public String save(UserRequestDTO userDTO) {
        try {
            if (userDTO == null) {
                throw new IllegalArgumentException("Dados do usuário não podem ser nulos.");
            }

            if (userDTO.getUsername() == null || userDTO.getPassword() == null || userDTO.getEmail() == null) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios.");
            }

            User newUser = new User();
            newUser.setUsername(userDTO.getUsername());
            newUser.setPassword(userDTO.getPassword());
            newUser.setEmail(userDTO.getEmail());

            this.tempRepository.save(newUser);

            return "Usuário salvo com sucesso!";

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao salvar usuário.", e);
        }
    }

    // DELETE
    public String delete(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID não pode ser nulo.");
            }


            if (!tempRepository.existsById(id)) {
                throw new RuntimeException("Usuário não encontrado para deletar.");
            }

            this.tempRepository.deleteById(id);

            return "Usuário deletado com sucesso!";

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao deletar usuário.", e);
        }
    }

    // UPDATE
    public String update(UserRequestDTO userDTO) {
        try {
            if (userDTO.getUserId() == null || userDTO.getUserId().toString().isBlank()) {
                throw new IllegalArgumentException("ID não pode ser nulo.");
            }


            Optional<User> existingUser = tempRepository.findById(userDTO.getUserId());

            if (existingUser.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado para atualização.");
            }
            User updatedUser = existingUser.get();

            if (!userDTO.getEmail().isEmpty()) {
                updatedUser.setEmail(userDTO.getEmail());
            }
            if (!userDTO.getUsername().isEmpty()) {
                updatedUser.setUsername(userDTO.getUsername());

            }
            if (!userDTO.getPassword().isEmpty()) {
                updatedUser.setPassword(userDTO.getPassword());
            }
            this.tempRepository.save(updatedUser);

            return "Usuário atualizado com sucesso!";

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao atualizar usuário.", e);
        }
    }

    // FIND BY ID
    public User findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID não pode ser nulo.");
            }

            return tempRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao buscar usuário.", e);
        }
    }

    // FIND ALL
    public List<User> findAll() {
        try {
            return this.tempRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao listar usuários.", e);
        }
    }
}