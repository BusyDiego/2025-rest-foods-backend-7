package ch.noseryoung.uek295adiedec.domain.bookstore;


import ch.noseryoung.uek295adiedec.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(UUID id, Book book) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookOptional.get().setTitle(book.getTitle());
            bookOptional.get().setPrice(book.getPrice());
            bookOptional.get().setLanguage(book.getLanguage());
            bookRepository.save(bookOptional.get());
            return bookOptional.get();
        }
        throw new NotFoundException("Book with id " + id + " not found");
    }

public void deleteBook(UUID id) {
 if (bookRepository.existsById(id)) {
     bookRepository.deleteById(id);
 }else{
     throw new NotFoundException("Book with id " + id + " not found");
}

    }
}
