package library_rest_spring_boot.library.service;
import library_rest_spring_boot.library.domain.entity.Loans;
import library_rest_spring_boot.library.repositories.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoansRepository loansRepository;

    public List<Loans> findAll() {
        return loansRepository.findAll();
    }

    public Optional<Loans> findById(Long id) {
        return loansRepository.findById(id);
    }

    public Loans save(Loans author) {
        return loansRepository.save(author);
    }

    public void deleteById(Long id) {
        loansRepository.deleteById(id);
    }
}
