package library_rest_spring_boot.library.service.author;

import library_rest_spring_boot.library.config.messages.MessageError;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.domain.entity.author.payload.AuthorDTO;
import library_rest_spring_boot.library.config.exception.NotFoundException;
import library_rest_spring_boot.library.config.exception.IllegalArgumentException;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.domain.entity.loans.Loans;
import library_rest_spring_boot.library.repositories.AuthorRepository;
import library_rest_spring_boot.library.repositories.BookRepository;
import library_rest_spring_boot.library.repositories.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final LoansRepository loansRepository;

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new NotFoundException(MessageError.AUTHOR_NOT_FOUND);
        }

        return authors.stream()
                .map(author -> new AuthorDTO(author.getName(), author.getBirthDate(), author.getNationality()))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorDTO(author.getName(), author.getBirthDate(), author.getNationality()))
                .orElseThrow(() -> new NotFoundException(MessageError.AUTHOR_NOT_FOUND));
    }


    @Override
    public AuthorDTO createAuthor(AuthorDTO form) {
        Optional<Author> existingAuthor = authorRepository.findByNameAndBirthDateAndNationality(
                form.getName(), form.getBirthDate(), form.getNationality());

        if (existingAuthor.isPresent()) {
            throw new IllegalArgumentException(MessageError.AUTHOR_ALREADY_EXISTS);
        }

        Author newAuthor = new Author();
        newAuthor.setName(form.getName());
        newAuthor.setBirthDate(form.getBirthDate());
        newAuthor.setNationality(form.getNationality());

        Author savedAuthor = authorRepository.save(newAuthor);

        return new AuthorDTO(savedAuthor.getName(), savedAuthor.getBirthDate(), savedAuthor.getNationality());
    }

    @Override
    public String deleteById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageError.NOT_FOUND_ID));

        List<Books> foundBooksRelatedAuthor =  bookRepository.findAllByAuthorId(author.getId());
        if (foundBooksRelatedAuthor.isEmpty()) {
            throw new NotFoundException(MessageError.BOOK_RELATED_AUTHOR_NOT_FOUND);
        }
        for (Books book : foundBooksRelatedAuthor) {
            List<Loans> foundLoansRelatedBooks = loansRepository.findAllByBook_Id(book.getId());
            if (!foundLoansRelatedBooks.isEmpty()) {
                throw new IllegalArgumentException(MessageError.AUTHOR_WITH_BOOKS_HAS_LOANS);
            }
        }
        authorRepository.deleteById(id);
        return "Author " + author.getName() + " successfully deleted";
    }

    @Override
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO form) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageError.NOT_FOUND_ID));

        author.setName(form.getName());
        author.setBirthDate(form.getBirthDate());
        author.setNationality(form.getNationality());
        Author updatedAuthor = authorRepository.save(author);

        return new AuthorDTO(updatedAuthor.getName(), updatedAuthor.getBirthDate(), updatedAuthor.getNationality());
    }
}
