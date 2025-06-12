package ch.noseryoung.backend.domain.menu;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;

    @Column(name = "chefs_choice")
    private Boolean chefsChoice;

    @Column(name = "image_url")
    private String imageUrl;
}
