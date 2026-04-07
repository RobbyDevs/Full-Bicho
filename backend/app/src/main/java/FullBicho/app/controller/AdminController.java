package FullBicho.app.controller;

import FullBicho.app.dto.AdminRequestDTO;
import FullBicho.app.dto.AdminUpdateDTO;
import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.dto.UserUpdateDTO;
import FullBicho.app.entity.User;
import FullBicho.app.service.AdminService;
import FullBicho.app.service.UserService;
import FullBicho.app.util.items.InputTreatment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app/admin")
public class AdminController {

    @Autowired
    InputTreatment treat;

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;


    @PostMapping("/saveUser")
    public ResponseEntity<String> SaveUser(@Valid @RequestBody AdminRequestDTO adminDTO){
        try {
            adminDTO.setCpf(treat.treatCPF(adminDTO.getCpf()));
            String message = adminService.saveUser(adminDTO);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody AdminUpdateDTO userTDO){
        try {

            userTDO.setCpf(treat.treatCPF(userTDO.getCpf()));

            String message = this.adminService.updateUser(userTDO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> findAllUsers(){
        try {
            List<User> list = this.adminService.findAllUsers();
            return new ResponseEntity<>(list, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/findUserByCpf")
    public ResponseEntity<User> findUserByCpf(@RequestParam String cpf){
        try {
            User user = adminService.findUserByCpf(treat.treatCPF(cpf));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}