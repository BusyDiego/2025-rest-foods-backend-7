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
    private String guest_name;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end_time;

    @Column(name = "table_id", nullable = false)
    private Integer table_id;

    @Column(name = "people_count", nullable = false)
    private Integer people_count;


}
