package ch.noseryoung.backend.domain.review;

import ch.noseryoung.backend.domain.bookstore.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Review")
@Entity
public class Review {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue
    private UUID id;
    
    private String content;
    private int stars;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}