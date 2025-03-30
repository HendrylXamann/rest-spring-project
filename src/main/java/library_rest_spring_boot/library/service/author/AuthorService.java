package library_rest_spring_boot.library.service.author;
import java.util.List;

import library_rest_spring_boot.library.domain.entity.author.payload.AuthorDTO;

public interface AuthorService {

    List<AuthorDTO> findAll();

    AuthorDTO findById(Long id);

    AuthorDTO createAuthor(AuthorDTO author);

    String deleteById(Long id);

    boolean existsById(Long id);

    AuthorDTO updateAuthor(Long id, AuthorDTO form);

}
