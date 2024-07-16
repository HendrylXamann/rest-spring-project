package library_rest_spring_boot.library.service;
import library_rest_spring_boot.library.domain.entity.Books;
import library_rest_spring_boot.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
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