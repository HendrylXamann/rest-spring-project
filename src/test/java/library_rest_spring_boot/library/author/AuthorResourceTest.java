package library_rest_spring_boot.library.author;
import java.util.Date;
import java.util.List;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.service.author.AuthorService;
import library_rest_spring_boot.library.resources.author.AuthorResource;

@ExtendWith(MockitoExtension.class)
public class AuthorResourceTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorResource authorResource;

    @Test
    public void testGetAllAuthors() {
    }

    @Test
    public void testGetAuthorById_Found() {
    }

    @Test
    public void testGetAuthorById_NotFound() {
    }

    @Test
    public void testCreateAuthor() {
    }

    @Test
    public void testUpdateAuthor_Found() {
    }

    @Test
    public void testUpdateAuthor_NotFound() {
    }

    @Test
    public void testDeleteAuthor() {

    }
}