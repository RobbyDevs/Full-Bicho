package FullBicho.app.controller;

import FullBicho.app.dto.UserLoginDTO;
import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.dto.UserUpdateDTO;
import FullBicho.app.entity.User;
import FullBicho.app.service.UserService;
import FullBicho.app.util.items.InputTreatment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
@CrossOrigin("*")
public class UserController {

    InputTreatment treat =  new InputTreatment();
    @Autowired
    private UserService userService;


    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserRequestDTO userDTO){
        try {
            userDTO.setCpf(treat.treatCPF(userDTO.getCpf()));
            String message = userService.saveUser(userDTO);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping ("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDTO userUpdtTDO){
        try {

            userUpdtTDO.setCpf(treat.treatCPF(userUpdtTDO.getCpf()));

            String message = this.userService.updateUser(userUpdtTDO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findUserByCpf")
    public ResponseEntity<User> findUserByCpf(@RequestParam String cpf){
        try {

            User user = userService.findUserByCpf(treat.treatCPF(cpf));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> userLogin(@RequestBody UserLoginDTO loginDTO){
        try {
            User user = userService.userLogin(loginDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            throw new RuntimeException("Usuário não encontrado");
        }
    }





}
