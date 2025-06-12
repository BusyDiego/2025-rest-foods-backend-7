package ch.noseryoung.backend.domain.menu;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items") // <-- Tabellenname in deiner DB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;
}
