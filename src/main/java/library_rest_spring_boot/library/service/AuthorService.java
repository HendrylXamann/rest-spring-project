package library_rest_spring_boot.library.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import library_rest_spring_boot.library.domain.entity.Author;
import library_rest_spring_boot.library.repositories.AuthorRepository;

@Component
public class AuthorService {
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
