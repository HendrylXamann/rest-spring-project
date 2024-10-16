package library_rest_spring_boot.library.resources.books;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.domain.entity.author.AuthorDTO;
import library_rest_spring_boot.library.domain.entity.book.BookDTO;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.service.author.AuthorService;
import library_rest_spring_boot.library.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "2. Books", description = "Operations related to books")
@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookResource {

    private final BookService bookService;
    private final AuthorService authorService;

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a book by ID")
    @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = Books.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Books> book = bookService.findById(id);
        return book.map(b -> ResponseEntity.ok(convertToDTO(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "200", description = "Book created", content = @Content(schema = @Schema(implementation = Books.class)))
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

    @Operation(summary = "Update a book by ID")
    @ApiResponse(responseCode = "200", description = "Book updated", content = @Content(schema = @Schema(implementation = Books.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
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

    @Operation(summary = "Delete a book by ID")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private BookDTO convertToDTO(Books book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationDate(book.getPublicationDate());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setAuthor(convertToDTO(book.getAuthor()));
        return bookDTO;
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBirthDate(author.getBirthDate());
        authorDTO.setNationality(author.getNationality());
        return authorDTO;
    }

    private Books convertToEntity(BookDTO bookDTO) {
        Books book = new Books();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setAuthor(convertToEntity(bookDTO.getAuthor()));
        return book;
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setNationality(authorDTO.getNationality());
        return author;
    }
}

