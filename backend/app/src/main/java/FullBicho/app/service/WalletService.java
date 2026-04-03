package FullBicho.app.service;

import FullBicho.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class WalletService {

    @Autowired
    UserService userService;

    public void credit(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
    }
}
