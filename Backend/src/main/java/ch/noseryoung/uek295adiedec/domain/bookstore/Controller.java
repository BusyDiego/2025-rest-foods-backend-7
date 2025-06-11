package ch.noseryoung.uek295adiedec.domain.bookstore;

import ch.noseryoung.uek295adiedec.domain.review.Review;
import ch.noseryoung.uek295adiedec.domain.review.ReviewRepository;
import ch.noseryoung.uek295adiedec.domain.review.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    //Erstellt Methode um alle Bücher abzurufen
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks());
    }

    //Ruft einzelne Bücher auf durch das Hinzufügen der ID am Ende der Adresse
    @GetMapping("/{bookId}")
    public ResponseEntity getBook(@PathVariable("bookId") UUID bookId) {
        Book book = bookService.getBook(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found " + bookId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book  createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book newBookData) {
        Book updated = bookService.updateBook(UUID.fromString(bookId), newBookData);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(UUID.fromString(bookId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviews());
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable("reviewId") String reviewId) {
        Review review = reviewService.getReview(UUID.fromString(reviewId));
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(review));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(UUID.fromString(reviewId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping ("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable String reviewId, @RequestBody Review newReview) {
      Review updated = reviewService.updateReview(UUID.fromString(reviewId), newReview);
      if (updated == null) {
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(updated);
    }


    @GetMapping("/price/ascending")
    public ResponseEntity<List<Book>> getBooksAscending() {
        return ResponseEntity.ok( bookRepository.sortByPrice());
    }

//@GetMapping("/reviews/numberStars/ascending")
  //  public ResponseEntity<List<Review>> getBooksNumberStars() {
    //    return ResponseEntity.ok(reviewRepository.sortByStars());
    //}

}
