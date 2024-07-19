package library_rest_spring_boot.library.book;
import java.util.List;
import java.util.Arrays;

import library_rest_spring_boot.library.domain.entity.author.Author;
import library_rest_spring_boot.library.domain.entity.author.AuthorDTO;
import library_rest_spring_boot.library.domain.entity.book.BookDTO;
import library_rest_spring_boot.library.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
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
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.service.BookService;
import library_rest_spring_boot.library.resources.books.BookResource;

@ExtendWith(MockitoExtension.class)
class BookResourceTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookResource bookResource;

    private Books book;
    private BookDTO bookDTO;
    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Author Name");

        book = new Books();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setIsbn("1234567890123");
        book.setAuthor(author);

        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Author Name");

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Book Title");
        bookDTO.setIsbn("1234567890123");
        bookDTO.setAuthor(authorDTO);
    }

    @Test
    void getAllBooks() {
        when(bookService.findAll()).thenReturn(Arrays.asList(book));

        List<BookDTO> result = bookResource.getAllBooks();

        assertEquals(1, result.size());
        assertEquals(bookDTO.getId(), result.get(0).getId());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void getBookById() {
        when(bookService.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(bookService.save(any(Books.class))).thenReturn(book);
        ResponseEntity<BookDTO> response = bookResource.updateBook(1L, bookDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService).save(any(Books.class));
    }

    @Test
    void createBook() {
        when(bookService.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(bookService.save(any(Books.class))).thenReturn(book);
        ResponseEntity<BookDTO> response = bookResource.updateBook(1L, bookDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService).save(any(Books.class));
    }

    @Test
    void updateBook() {
        when(bookService.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(bookService.save(any(Books.class))).thenReturn(book);
        ResponseEntity<BookDTO> response = bookResource.updateBook(1L, bookDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService).save(any(Books.class));
    }

    @Test
    void deleteBook() {
        doNothing().when(bookService).deleteById(1L);

        ResponseEntity<Void> result = bookResource.deleteBook(1L);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(bookService, times(1)).deleteById(1L);
    }
}