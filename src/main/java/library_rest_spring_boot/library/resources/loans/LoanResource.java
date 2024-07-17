package library_rest_spring_boot.library.resources.loans;
import library_rest_spring_boot.library.domain.entity.Loans;
import library_rest_spring_boot.library.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanResource {

    private LoanService loanService;

    @GetMapping
    public List<Loans> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loans> getLoanById(@PathVariable Long id) {
        Optional<Loans> loan = loanService.findById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Loans createLoan(@RequestBody Loans loans) {
        return loanService.save(loans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loans> updateLoan(@PathVariable Long id, @RequestBody Loans loanDetails) {
        Optional<Loans> loan = loanService.findById(id);
        if (loan.isPresent()) {
            Loans updatedLoan = loan.get();
            updatedLoan.setLoanDate(loanDetails.getLoanDate());
            updatedLoan.setReturnDate(loanDetails.getReturnDate());
            updatedLoan.setBook(loanDetails.getBook());
            updatedLoan.setUserId(loanDetails.getUserId());
            return ResponseEntity.ok(loanService.save(updatedLoan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
