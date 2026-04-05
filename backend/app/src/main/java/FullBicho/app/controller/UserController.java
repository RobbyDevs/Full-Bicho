package FullBicho.app.controller;

import FullBicho.app.dto.UserRequestDTO;
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


    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@Valid @RequestParam String cpf){

        String tempCPF = treat.treatCPF(cpf);
        try {
            String message = userService.deleteUser(cpf);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping("/updateUser/")
    public ResponseEntity<String> updateUser( @RequestBody UserRequestDTO userTDO){
        try {

            String message = this.userService.updateUser(userTDO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findUserById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> findAllUsers(){
        try {
            List<User> list = this.userService.findAllUsers();
            return new ResponseEntity<>(list, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
