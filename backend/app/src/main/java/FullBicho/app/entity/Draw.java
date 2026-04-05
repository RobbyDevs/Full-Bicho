package FullBicho.app.entity;


import FullBicho.app.util.items.DrawStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Draw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drawId;

    private LocalDateTime drawTime;

    @Enumerated(EnumType.STRING)
    private DrawStatus status; // OPEN, CLOSED

    private LocalDateTime createdAt;


    public boolean isOpen() {
        return this.status == DrawStatus.OPEN;
    }

    public void close() {
        if (this.status == DrawStatus.CLOSED) {
            throw new IllegalStateException("Draw already closed");
        }
        this.status = DrawStatus.CLOSED;
    }


}
