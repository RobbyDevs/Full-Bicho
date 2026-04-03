package FullBicho.app.controller;

import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.entity.User;
import FullBicho.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserRequestDTO userDTO){
        try {

            String message = userService.save(userDTO);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete( @PathVariable Long id){

        try {
            String message = userService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping("/update/")
    public ResponseEntity<String> update( @RequestBody UserRequestDTO userTDO){
        try {

            String message = this.userService.update(userTDO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll(){
        try {
            List<User> list = this.userService.findAll();
            return new ResponseEntity<>(list, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
