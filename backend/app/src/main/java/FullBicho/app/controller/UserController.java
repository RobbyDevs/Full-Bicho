package FullBicho.app.controller;

import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.dto.UserUpdateDTO;
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




}
