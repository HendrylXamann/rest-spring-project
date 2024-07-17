package library_rest_spring_boot.library.resources.author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import library_rest_spring_boot.library.domain.entity.Author;
import library_rest_spring_boot.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorResource {

    private AuthorService authorService;

    @Operation(summary = "Get all authors", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class)))
    })
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @Operation(summary = "Get an author by ID", responses = {
            @ApiResponse(description = "Author found", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(description = "Author not found", responseCode = "404")
    }, parameters = {
            @Parameter(name = "id", description = "Author ID")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new author", responses = {
            @ApiResponse(description = "Author created", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Author to be created", required = true, content = @Content(schema = @Schema(implementation = Author.class)))
    )

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @Operation(summary = "Update an author", responses = {
            @ApiResponse(description = "Author updated", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(description = "Author not found", responseCode = "404")
    }, parameters = {
            @Parameter(name = "id", description = "Author ID")
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated author details", required = true, content = @Content(schema = @Schema(implementation = Author.class)))
    )

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Optional<Author> author = authorService.findById(id);
        if (author.isPresent()) {
            Author updatedAuthor = author.get();
            updatedAuthor.setName(authorDetails.getName());
            return ResponseEntity.ok(authorService.save(updatedAuthor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an author", responses = {
            @ApiResponse(description = "Author deleted", responseCode = "204"),
            @ApiResponse(description = "Author not found", responseCode = "404")
    }, parameters = {
            @Parameter(name = "id", description = "Author ID")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
