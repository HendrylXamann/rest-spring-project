package library_rest_spring_boot.library.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import library_rest_spring_boot.library.domain.entity.Books;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
}