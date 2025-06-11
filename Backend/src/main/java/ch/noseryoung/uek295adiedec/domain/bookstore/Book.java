package ch.noseryoung.uek295adiedec.domain.bookstore;

import ch.noseryoung.uek295adiedec.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Book")
@Entity
public class Book {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue
    private UUID id;
    private  String title;
    private   String language;
    private double price;


    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}
