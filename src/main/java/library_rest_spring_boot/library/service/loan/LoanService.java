package library_rest_spring_boot.library.service.loan;
import java.util.List;
import java.util.Optional;
import library_rest_spring_boot.library.domain.entity.loans.Loans;
import library_rest_spring_boot.library.repositories.LoansRepository;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {

     List<Loans> findAll();

     Optional<Loans> findById(Long id);

     Loans save(Loans author);

     void deleteById(Long id);
}
