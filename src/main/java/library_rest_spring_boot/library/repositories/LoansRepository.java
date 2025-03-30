package library_rest_spring_boot.library.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import library_rest_spring_boot.library.domain.entity.loans.Loans;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    List<Loans> findAllByBook_Id(Long bookId);
}
