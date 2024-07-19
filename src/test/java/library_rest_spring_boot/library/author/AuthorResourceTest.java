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
import library_rest_spring_boot.library.service.AuthorService;
import library_rest_spring_boot.library.resources.author.AuthorResource;

@ExtendWith(MockitoExtension.class)
public class AuthorResourceTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorResource authorResource;

    @Test
    public void testGetAllAuthors() {
        Author author1 = new Author("Author 1", new Date(), "Nationality 1");
        Author author2 = new Author("Author 2", new Date(), "Nationality 2");
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorService.findAll()).thenReturn(authors);
        ResponseEntity<List<Author>> response = authorResource.getAllAuthors();

        assertEquals(2, response.getBody().size());
        assertEquals("Author 1", response.getBody().get(0).getName());
        assertEquals("Author 2", response.getBody().get(1).getName());
        verify(authorService, times(1)).findAll();
    }

    @Test
    public void testGetAuthorById_Found() {
        Author author = new Author("Clovis de Barros, o Cara", new Date(), "Bulgaro");
        when(authorService.findById(1L)).thenReturn(Optional.of(author));
        ResponseEntity<Author> response = authorResource.getAuthorById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(author, response.getBody());
        verify(authorService, times(1)).findById(1L);
    }

    @Test
    public void testGetAuthorById_NotFound() {
        when(authorService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Author> response = authorResource.getAuthorById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(authorService, times(1)).findById(1L);
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author("Geoffrey Blainey", new Date(), "Estadunidense");
        when(authorService.save(any(Author.class))).thenReturn(author);
        Author createdAuthor = authorResource.createAuthor(author);

        assertEquals(author.getName(), createdAuthor.getName());
        verify(authorService, times(1)).save(author);
    }

    @Test
    public void testUpdateAuthor_Found() {
        Author existingAuthor = new Author("Existing Author", new Date(), "Existing Nationality");
        Author updatedDetails = new Author("Updated Author", new Date(), "Updated Nationality");
        when(authorService.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(authorService.save(any(Author.class))).thenReturn(updatedDetails);
        ResponseEntity<Author> response = authorResource.updateAuthor(1L, updatedDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDetails.getName(), response.getBody().getName());
        verify(authorService, times(1)).findById(1L);
        verify(authorService, times(1)).save(existingAuthor);
    }

    @Test
    public void testUpdateAuthor_NotFound() {
        Author updatedDetails = new Author("Updated Author", new Date(), "Updated Nationality");
        when(authorService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Author> response = authorResource.updateAuthor(1L, updatedDetails);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(authorService, times(1)).findById(1L);
        verify(authorService, never()).save(any(Author.class));
    }

    @Test
    public void testDeleteAuthor() {
        doNothing().when(authorService).deleteById(1L);
        ResponseEntity<Void> response = authorResource.deleteAuthor(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorService, times(1)).deleteById(1L);
    }
}