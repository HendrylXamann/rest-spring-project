package library_rest_spring_boot.library.resources.books;
import library_rest_spring_boot.library.domain.entity.Books;
import library_rest_spring_boot.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookResource {

    private BookService bookService;

    @GetMapping
    public List<Books> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Optional<Books> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Books createBook(@RequestBody Books books) {
        return bookService.save(books);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

