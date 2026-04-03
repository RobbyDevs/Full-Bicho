package FullBicho.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;


    private String password;
    private String email;
    private Double balance = 1000.00;


    public void debit(Double amount) {
        this.setBalance(this.getBalance() - amount);
    }
    public void credit(Double amount) {
        this.setBalance(this.getBalance() + amount);
    }
}
