package library_rest_spring_boot.library.service.book;

import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.repositories.BookRepository;
import library_rest_spring_boot.library.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Books> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Books save(Books book) {
        Long authorId = book.getAuthor().getId();
        if (!authorService.existsById(authorId)) {
            throw new IllegalArgumentException("Author with id " + authorId + " does not exist.");
        }
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
