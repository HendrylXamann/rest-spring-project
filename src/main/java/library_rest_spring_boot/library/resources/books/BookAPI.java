package library_rest_spring_boot.library.resources.books;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.domain.entity.book.payload.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "2. Books", description = "Operations related to books")
@RequestMapping("/api/books")
public interface BookAPI {

    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    List<BookDTO> getAllBooks();

    @Operation(summary = "Get a book by ID")
    @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = Books.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    ResponseEntity<BookDTO> getBookById(@PathVariable Long id);

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "200", description = "Book created", content = @Content(schema = @Schema(implementation = Books.class)))
    @PostMapping
    ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO);

    @Operation(summary = "Update a book by ID")
    @ApiResponse(responseCode = "200", description = "Book updated", content = @Content(schema = @Schema(implementation = Books.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @PutMapping("/{id}")
    ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO);

    @Operation(summary = "Delete a book by ID")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable Long id);
}
