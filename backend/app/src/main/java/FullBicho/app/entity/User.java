package FullBicho.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    private String username; //RequestBody
    private String password; //RequestBody
    private String email; //RequestBody

    private Double balance = 1000.00;


    public void debit(Double amount) {
        this.setBalance(this.getBalance() - amount);
    }
    public void credit(Double amount) {
        this.setBalance(this.getBalance() + amount);
    }
}
