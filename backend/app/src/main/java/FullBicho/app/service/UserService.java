package FullBicho.app.service;

import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.dto.UserUpdateDTO;
import FullBicho.app.entity.User;
import FullBicho.app.repository.UserRepository;
import FullBicho.app.util.items.InputTreatment;
import FullBicho.app.util.items.RoleType;
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

    // CREATE PUBLIC
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
            newUser.setRole(RoleType.REGULAR);

            this.userRepository.save(newUser);

            return "Usuário salvo com sucesso!";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    // UPDATE
    public String updateUser(UserUpdateDTO userDTO) {
        try{

            //checar se user existe
            User foundUser = userRepository.findByCpf(userDTO.getCpf());
            if (foundUser == null) {throw new RuntimeException("USUÁRIO NÃO ENCONTRADO!!!");}

            //checar se senha bate
            if (!(foundUser.getPassword().equals(userDTO.getPassword()))) {
                throw new IllegalArgumentException("SENHA INCORRETA!!!");
            }


            //CRIAR NOVO USER


            if (!userDTO.getUsername().isBlank()) {
                foundUser.setUsername(userDTO.getUsername().toUpperCase(Locale.getDefault()));
            }
            if (!userDTO.getEmail().isBlank()) {
                foundUser.setEmail(userDTO.getEmail().toLowerCase(Locale.getDefault()));
            }
//            if (userDTO.getCpf().equals(foundUser.getCpf())) {
//                newUser.setCpf(userDTO.getCpf());
//            }

            userRepository.save(foundUser);




        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "USUÁRIO ATUALIZADO COM SUCESSO!!!";
    }




}