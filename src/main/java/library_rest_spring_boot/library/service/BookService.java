package library_rest_spring_boot.library.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Books> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Books save(Books book) {
        Long authorId = book.getAuthor().getId();
        if (!authorService.existsById(authorId)) {
            throw new IllegalArgumentException("Author with id " + authorId + " does not exist.");
        }
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}