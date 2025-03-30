package library_rest_spring_boot.library.resources.author;
import jakarta.validation.Valid;
import library_rest_spring_boot.library.domain.entity.author.payload.AuthorDTO;
import library_rest_spring_boot.library.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorResource implements AuthorAPI{
    private final AuthorService authorService;

    @Override
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO author) {
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDetails) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDetails));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        String message = authorService.deleteById(id);
        return ResponseEntity.ok(message);
    }
}
