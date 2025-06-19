package ch.noseryoung.backend.domain.reservation;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "table_id", nullable = false)
    private Integer tableId;

    @Column(name = "people_count", nullable = false)
    private Integer peopleCount;

    @Column(name = "email")
    private String email;

    @Column(name = "special_requests")
    private String specialRequests;

    @Column(name = "status")
    private String status = "PENDING";
    
    @Column(name = "date")
    private LocalDate date;
}
