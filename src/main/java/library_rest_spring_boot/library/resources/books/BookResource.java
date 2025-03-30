package library_rest_spring_boot.library.resources.books;
import jakarta.validation.Valid;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.domain.entity.author.payload.AuthorDTO;
import library_rest_spring_boot.library.domain.entity.book.payload.BookDTO;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.service.author.AuthorService;
import library_rest_spring_boot.library.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BookResource implements BookAPI {
    private final BookService bookService;
    private final AuthorService authorService;

    @Override
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
//        return bookService.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Books> book = bookService.findById(id);
        return book.map(b -> ResponseEntity.ok(convertToDTO(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Books book = convertToEntity(bookDTO);
        Author author = book.getAuthor();
        if (author != null) {
            if (author.getId() != null) {
                if (!authorService.existsById(author.getId())) {
                    throw new IllegalArgumentException("Author with id " + author.getId() + " does not exist.");
                }
            } else {
                author = authorService.save(author);
                book.setAuthor(author);
            }
        }
        Books savedBook = bookService.save(book);
        return ResponseEntity.ok(convertToDTO(savedBook));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Books> book = bookService.findById(id);
        if (book.isPresent()) {
            Books updatedBook = book.get();
            updatedBook.setTitle(bookDTO.getTitle());
            updatedBook.setIsbn(bookDTO.getIsbn());
            updatedBook.setPublicationDate(bookDTO.getPublicationDate());
            updatedBook.setNumberOfPages(bookDTO.getNumberOfPages());
            AuthorDTO authorDTO = bookDTO.getAuthor();
            if (authorDTO != null) {
                Author author = convertToEntity(authorDTO);
                updatedBook.setAuthor(author);
            }
            Books savedBook = bookService.save(updatedBook);
            return ResponseEntity.ok(convertToDTO(savedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private BookDTO convertToDTO(Books book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationDate(book.getPublicationDate());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setAuthor(convertToDTO(book.getAuthor()));
        return bookDTO;
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());
        authorDTO.setBirthDate(author.getBirthDate());
        authorDTO.setNationality(author.getNationality());
        return authorDTO;
    }

    private Books convertToEntity(BookDTO bookDTO) {
        Books book = new Books();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setAuthor(convertToEntity(bookDTO.getAuthor()));
        return book;
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setNationality(authorDTO.getNationality());
        return author;
    }
}

