package library_rest_spring_boot.library.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import library_rest_spring_boot.library.domain.entity.book.Books;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    List<Books> findAllByAuthorId(Long authorId);
}