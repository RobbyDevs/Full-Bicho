package FullBicho.app.dto;

import FullBicho.app.util.DrawStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DrawRequestDTO {
    private LocalDateTime drawTime;
    @Enumerated(EnumType.STRING)
    private DrawStatus status; // OPEN, CLOSED
    private LocalDateTime createdAt;
}
