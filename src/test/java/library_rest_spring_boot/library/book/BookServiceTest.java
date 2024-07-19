package library_rest_spring_boot.library.book;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import library_rest_spring_boot.library.service.BookService;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.repositories.BookRepository;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Books book1 = new Books();
        Books book2 = new Books();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        List<Books> result = bookService.findAll();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_Found() {
        Books book = new Books();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Books> result = bookService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(book, result.get());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Books> result = bookService.findById(1L);

        assertFalse(result.isPresent());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteById(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
