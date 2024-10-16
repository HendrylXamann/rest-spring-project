package library_rest_spring_boot.library.service.author;
import java.util.List;
import java.util.Optional;

import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Author save(Author author);

    void deleteById(Long id);

    boolean existsById(Long id);

    Author updateAuthor(Author authorDetails);

}
