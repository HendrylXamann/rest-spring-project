package library_rest_spring_boot.library.resources.books;
import library_rest_spring_boot.library.domain.entity.Books;
import library_rest_spring_boot.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/books")
public class BookResource {

    private BookService bookService;

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public List<Books> getAllBooks() {
        return bookService.findAll();
    }

    @Operation(summary = "Get a book by ID")
    @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = Books.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Optional<Books> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "200", description = "Book created", content = @Content(schema = @Schema(implementation = Books.class)))
    @PostMapping
    public Books createBook(@RequestBody Books books) {
        return bookService.save(books);
    }

    @Operation(summary = "Update a book by ID")
    @ApiResponse(responseCode = "200", description = "Book updated", content = @Content(schema = @Schema(implementation = Books.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books bookDetails) {
        Optional<Books> book = bookService.findById(id);
        if (book.isPresent()) {
            Books updatedBook = book.get();
            updatedBook.setTitle(bookDetails.getTitle());
            updatedBook.setIsbn(bookDetails.getIsbn());
            updatedBook.setPublicationDate(bookDetails.getPublicationDate());
            updatedBook.setNumberOfPages(bookDetails.getNumberOfPages());
            updatedBook.setAuthor(bookDetails.getAuthor());
            return ResponseEntity.ok(bookService.save(updatedBook));
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
}

