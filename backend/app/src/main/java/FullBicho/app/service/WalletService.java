package FullBicho.app.service;

import FullBicho.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class WalletService {

    @Autowired
    UserService userService;

    public void creditValue(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
    }

    public void debitValue(User user, double amount) {
        if (user.getBalance() < amount ||  user.getBalance() <= 0) {
            throw new IllegalStateException("SALDO INSUFICIENTE!!");
        }

        user.setBalance(user.getBalance() - amount);
    }
}
