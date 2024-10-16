package library_rest_spring_boot.library.service.author;

import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.exception.NotFoundException;
import library_rest_spring_boot.library.repositories.AuthorRepository;
import library_rest_spring_boot.library.exception.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {

        if (!existsById(id)) {
            throw new NotFoundException("Author not found with id " + id);
        }
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        if (author == null ||
                author.getName() == null || author.getName().isEmpty() ||
                author.getBirthDate() == null ||
                author.getNationality() == null || author.getNationality().isEmpty()) {
            throw new IllegalArgumentException("Invalid author data: all fields are mandatory");
        }
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new NotFoundException("id cannot be null");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public Author updateAuthor(Author authorDetails) {
        if (authorDetails.getId() == null) {
            throw new NotFoundException("Author ID must be provided");
        }
        if ((authorDetails.getName() == null || authorDetails.getName().isEmpty()) &&
                authorDetails.getBirthDate() == null &&
                (authorDetails.getNationality() == null || authorDetails.getNationality().isEmpty())) {
            throw new IllegalArgumentException("At least one of name, birth date, or nationality must be provided");
        }
        Optional<Author> author = findById(authorDetails.getId());
        if (author.isPresent()) {
            Author updatedAuthor = author.get();
            if (authorDetails.getName() != null && !authorDetails.getName().isEmpty()) {
                updatedAuthor.setName(authorDetails.getName());
            }
            if (authorDetails.getBirthDate() != null) {
                updatedAuthor.setBirthDate(authorDetails.getBirthDate());
            }
            if (authorDetails.getNationality() != null && !authorDetails.getNationality().isEmpty()) {
                updatedAuthor.setNationality(authorDetails.getNationality());
            }
            return save(updatedAuthor);
        } else {
            throw new NotFoundException("Author not found with id " + authorDetails.getId());
        }
    }
}
