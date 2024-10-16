package library_rest_spring_boot.library.resources.author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Tag(name = "1. Authors", description = "Operations related to authors")
@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorResource {
    private final AuthorService authorService;

    @Operation(summary = "Get all authors", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class)))
    })
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
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
    public Author createAuthor(@Valid @RequestBody Author author) {
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
    public ResponseEntity<Author> updateAuthor(@RequestBody Author authorDetails) {
        try {
            Author updatedAuthor = authorService.updateAuthor(authorDetails);
            return ResponseEntity.ok(updatedAuthor);
        } catch (NotFoundException e) {
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
        try {
            authorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
