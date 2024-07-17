package library_rest_spring_boot.library.book;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import library_rest_spring_boot.library.domain.entity.Books;
import library_rest_spring_boot.library.service.BookService;
import library_rest_spring_boot.library.resources.books.BookResource;

@ExtendWith(MockitoExtension.class)
public class BookResourceTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookResource bookResource;

    @Test
    public void testGetAllBooks() {
        Books book1 = new Books();
        Books book2 = new Books();
        when(bookService.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Books> result = bookResource.getAllBooks();

        assertEquals(2, result.size());
        verify(bookService, times(1)).findAll();
    }

    @Test
    public void testGetBookById_Found() {
        Books book = new Books();
        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        ResponseEntity<Books> response = bookResource.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    public void testGetBookById_NotFound() {
        when(bookService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Books> response = bookResource.getBookById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    public void testCreateBook() {
        Books book = new Books();
        when(bookService.save(any(Books.class))).thenReturn(book);

        Books result = bookResource.createBook(book);

        assertEquals(book, result);
    }

    @Test
    public void testUpdateBook_Found() {
        Books existingBook = new Books();
        Books updatedBook = new Books();
        when(bookService.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookService.save(any(Books.class))).thenReturn(updatedBook);

        ResponseEntity<Books> response = bookResource.updateBook(1L, updatedBook);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, times(1)).save(existingBook);
    }

    @Test
    public void testUpdateBook_NotFound() {
        Books updatedBook = new Books();
        when(bookService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Books> response = bookResource.updateBook(1L, updatedBook);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, never()).save(any(Books.class));
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookService).deleteById(1L);

        ResponseEntity<Void> response = bookResource.deleteBook(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteById(1L);
    }
}