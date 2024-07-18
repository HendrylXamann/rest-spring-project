package library_rest_spring_boot.library.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import library_rest_spring_boot.library.domain.entity.Books;
import library_rest_spring_boot.library.repositories.BookRepository;

@Component
public class BookService {
    private BookRepository bookRepository;

    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Books> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Books save(Books author) {
        return bookRepository.save(author);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}