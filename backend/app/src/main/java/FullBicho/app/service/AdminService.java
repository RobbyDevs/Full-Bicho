package FullBicho.app.service;

import FullBicho.app.dto.AdminRequestDTO;
import FullBicho.app.dto.AdminUpdateDTO;
import FullBicho.app.entity.User;
import FullBicho.app.repository.UserRepository;
import FullBicho.app.util.items.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AdminService extends UserService{

    @Autowired
    private UserRepository userRepository;



    public String saveUser(AdminRequestDTO userDTO) {
        try {
            //Checar CPF
            if (userRepository.existsByCpf(userDTO.getCpf())) {
                throw new  IllegalArgumentException("CPF já cadastrado!!!");
            }

            if (!(userDTO.getRoleType().equals(RoleType.ADMIN)) && !(userDTO.getRoleType().equals(RoleType.REGULAR))) {
                throw new IllegalArgumentException("TIPO DE USUÁRIO INVÁLIDO!!!");

            }

            User newUser = new User();
            newUser.setUsername(userDTO.getUsername().toUpperCase(Locale.getDefault())); // atribui username em Upercase
            newUser.setPassword(userDTO.getPassword());
            newUser.setEmail(userDTO.getEmail().toLowerCase(Locale.getDefault())); // atribui email em lowecase
            newUser.setCpf(userDTO.getCpf());
            newUser.setRole(userDTO.getRoleType());

            this.userRepository.save(newUser);

            return "Usuário salvo com sucesso!";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public String updateUser(AdminUpdateDTO adminDTO) {
        try{

            //checar se user existe
            User foundUser = userRepository.findByCpf(adminDTO.getCpf());
            if (foundUser == null) {throw new RuntimeException("USUÁRIO NÃO ENCONTRADO!!!");}

            //checar se senha bate
            if (!(foundUser.getPassword().equals(adminDTO.getPassword()))) {
                throw new IllegalArgumentException("SENHA INCORRETA!!!");
            }

            //CRIAR NOVO USER

            if (!adminDTO.getUsername().isBlank()) {
                foundUser.setUsername(adminDTO.getUsername().toUpperCase(Locale.getDefault()));
            }
            if (!adminDTO.getEmail().isBlank()) {
                foundUser.setEmail(adminDTO.getEmail().toLowerCase(Locale.getDefault()));
            }
            if (!(adminDTO.getRole() == null)){
                foundUser.setRole(adminDTO.getRole());
            }
            //atualiza
            userRepository.save(foundUser);

        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "USUÁRIO ATUALIZADO COM SUCESSO!!!";
    }

    // FIND ALL
    public List<User> findAllUsers() {
        try {
            return this.userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar o banco de dados ao listar usuários.", e);
        }
    }

    // FIND BY ID
    public User findUserByCpf(String cpf) {
        try {
            User foundUser = userRepository.findByCpf(cpf);
            if (foundUser == null) {
                throw new RuntimeException("USUÁRIO NÃO ENCONTRADO!!!");
            }

            return this.userRepository.findByCpf(cpf);
        }
        catch (Exception e) {
            throw new RuntimeException (e.getMessage());
        }
    }

}
