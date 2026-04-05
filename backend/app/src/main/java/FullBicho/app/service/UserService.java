package FullBicho.app.service;

import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.entity.User;
import FullBicho.app.repository.UserRepository;
import FullBicho.app.util.items.InputTreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InputTreatment inputTreatment;

    // CREATE
    public String saveUser(UserRequestDTO userDTO) {
        try {
            //Checar CPF
            if (userRepository.existsByCpf(userDTO.getCpf())) {
                throw new  IllegalArgumentException("CPF já cadastrado!!!");
            }

            User newUser = new User();
            newUser.setUsername(userDTO.getUsername().toUpperCase(Locale.getDefault())); // atribui username em Upercase
            newUser.setPassword(userDTO.getPassword());
            newUser.setEmail(userDTO.getEmail().toLowerCase(Locale.getDefault())); // atribui email em lowecase
            newUser.setCpf(userDTO.getCpf());


            this.userRepository.save(newUser);

            return "Usuário salvo com sucesso!";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // DELETE
    public String deleteUser(String cpf) {
        try {
            if (userRepository.existsByCpf(cpf)){
                userRepository.deleteByCpf(cpf);
            }
            else  {
                throw new  IllegalArgumentException("CPF JÁ DADASTRADO !!!");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "USUÁRIO DELETADO COM SUCESSO!!!";
    }

    // UPDATE
    public String updateUser(UserRequestDTO userDTO) {
        return "ok";
    }

    // FIND BY ID
    public User findUserById(Long id) {
        return null;
    }

    // FIND ALL
    public List<User> findAllUsers() {
        try {
            return this.userRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao listar usuários.", e);
        }
    }
}