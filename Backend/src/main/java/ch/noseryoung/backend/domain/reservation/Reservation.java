package ch.noseryoung.backend.domain.reservation;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    
    private String email;
    
    private String phone;
    
    private LocalDateTime reservationDateTime;
    
    private Integer numberOfPeople;
    
    private String specialRequests;
    
    private String status = "PENDING";
}