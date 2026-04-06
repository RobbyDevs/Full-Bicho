package FullBicho.app.entity;


import FullBicho.app.util.items.RoleType;
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

    @Enumerated(EnumType.STRING)
    private RoleType role; //Auto select - default: "REGULAR"
    private Double balance = 1000.00;


    public void debit(Double amount) {
        this.setBalance(this.getBalance() - amount);
    }
    public void credit(Double amount) {
        this.setBalance(this.getBalance() + amount);
    }
}
