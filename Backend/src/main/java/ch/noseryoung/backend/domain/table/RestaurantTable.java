package ch.noseryoung.backend.domain.table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false)
    private Integer tableNumber;

    @Column(nullable = false)
    private Integer seats;

    @Column(nullable = false)
    private Boolean available;
}
