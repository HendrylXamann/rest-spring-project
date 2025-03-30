package library_rest_spring_boot.library.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import library_rest_spring_boot.library.domain.entity.author.Author;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndBirthDateAndNationality(String name, Date birthDate, String nationality);
}

