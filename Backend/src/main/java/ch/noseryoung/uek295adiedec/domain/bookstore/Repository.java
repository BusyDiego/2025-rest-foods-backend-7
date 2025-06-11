package  ch.noseryoung.uek295adiedec.domain.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {


    @Query("SELECT b FROM Book b ORDER BY b.price")
    List<Book> sortByPrice();

}
