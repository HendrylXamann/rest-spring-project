package library_rest_spring_boot.library.service.loan;

import library_rest_spring_boot.library.domain.entity.loans.Loans;
import library_rest_spring_boot.library.repositories.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService{
    private final LoansRepository loansRepository;

    @Override
    public List<Loans> findAll() {
        return loansRepository.findAll();
    }

    @Override
    public Optional<Loans> findById(Long id) {
        return loansRepository.findById(id);
    }

    @Override
    public Loans save(Loans author) {
        return loansRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        loansRepository.deleteById(id);
    }
}
