package library_rest_spring_boot.library.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import library_rest_spring_boot.library.domain.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

