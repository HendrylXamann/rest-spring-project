package library_rest_spring_boot.library.author;
import library_rest_spring_boot.library.service.author.AuthorService;
import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {

    }
    @Test
    void testFindAll() {

    }

    @Test
    void testSaveAuthor() {

    }

    @Test
    void testDeleteById() {

    }
}
