package library_rest_spring_boot.library.service.book;
import java.util.List;
import java.util.Optional;

import library_rest_spring_boot.library.service.author.AuthorService;
import library_rest_spring_boot.library.domain.entity.book.Books;
import library_rest_spring_boot.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

     List<Books> findAll();

     Optional<Books> findById(Long id);

     Books save(Books book);

     void deleteById(Long id);
}