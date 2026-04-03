package FullBicho.app.controller;

import FullBicho.app.dto.BetRequestDTO;
import FullBicho.app.entity.Bet;
import FullBicho.app.service.BetService;
import FullBicho.app.service.DrawService;
import FullBicho.app.service.UserService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/user/bet")
public class BetController {
    @Autowired
    private BetService betService;
    @Autowired
    private UserService userService;
    @Autowired
    private DrawService drawService;



    @PostMapping("/placeBet")
    public ResponseEntity<String> placeBet(@RequestBody BetRequestDTO betRequest){
        try {

            String message = betService.placeBet(betRequest);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/autoPlaceBet")
    public ResponseEntity<String> autoPlaceBet(@RequestBody BetRequestDTO betRequest){
        try {

            String message = betService.autoPlaceBet(betRequest);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
