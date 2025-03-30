package library_rest_spring_boot.library.resources.author;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.domain.entity.author.payload.AuthorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. Authors", description = "Operations related to authors")
@RequestMapping("/api/authors")
public interface AuthorAPI {

    @Operation(summary = "Get all authors", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class)))
    })
    @GetMapping
    ResponseEntity<List<AuthorDTO>> getAllAuthors();

    @Operation(summary = "Get an author by ID", responses = {
            @ApiResponse(description = "Author found", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(description = "Author not found", responseCode = "404")
    }, parameters = {
            @Parameter(name = "id", description = "Author ID")
    })
    @GetMapping("/{id}")
    ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id);

    @Operation(summary = "Create a new author", responses = {
            @ApiResponse(description = "Author created", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Author to be created", required = true, content = @Content(schema = @Schema(implementation = Author.class)))
    )
    @PostMapping
    ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO author);

    @Operation(summary = "Update an author", responses = {
            @ApiResponse(description = "Author updated", responseCode = "200", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(description = "Author not found", responseCode = "404")
    }, parameters = {
            @Parameter(name = "id", description = "Author ID")
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated author details", required = true, content = @Content(schema = @Schema(implementation = Author.class)))
    )
    @PutMapping("/{id}")
    ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDetails);

    @Operation(summary = "Delete an author", responses = {
            @ApiResponse(description = "Author deleted", responseCode = "204"),
            @ApiResponse(description = "Author not found", responseCode = "404")
    }, parameters = {
            @Parameter(name = "id", description = "Author ID")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAuthor(@PathVariable Long id);
}
