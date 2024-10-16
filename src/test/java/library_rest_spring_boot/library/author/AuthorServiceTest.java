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
        Long authorId = 1L;
        Author mockAuthor = new Author();
        mockAuthor.setName("Peter");
        mockAuthor.setNationality("American");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(mockAuthor));

        Optional<Author> foundAuthor = authorService.findById(authorId);

        assertTrue(foundAuthor.isPresent(), "Author should be found");
        assertEquals("Peter", foundAuthor.get().getName(), "Author name should match");
        verify(authorRepository).findById(authorId);
    }
    @Test
    void testFindAll() {
        List<Author> mockAuthors = Arrays.asList(
                new Author("Author 1", new Date(), "Nationality 1"),
                new Author("Author 2", new Date(), "Nationality 2")
        );

        when(authorRepository.findAll()).thenReturn(mockAuthors);

        List<Author> authors = authorService.findAll();

        assertNotNull(authors, "Authors list should not be null");
        assertEquals(2, authors.size(), "Authors list size should match");
        assertTrue(authors.containsAll(mockAuthors), "Authors list should contain all mock authors");

        verify(authorRepository).findAll();
    }

    @Test
    void testSaveAuthor() {
        Author newAuthor = new Author();
        newAuthor.setName("Peter");
        newAuthor.setNationality("American");

        when(authorRepository.save(any(Author.class))).thenReturn(newAuthor);

        Author savedAuthor = authorService.save(newAuthor);

        assertNotNull(savedAuthor, "Saved author should not be null");
        assertEquals("Peter", savedAuthor.getName(), "Author name should match");
        verify(authorRepository).save(any(Author.class));
    }

    @Test
    void testDeleteById() {
        Long authorId = 1L;

        doNothing().when(authorRepository).deleteById(authorId);

        authorService.deleteById(authorId);

        verify(authorRepository).deleteById(authorId);
    }
}
