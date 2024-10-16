package library_rest_spring_boot.library.resources.loans;
import io.swagger.v3.oas.annotations.tags.Tag;
import library_rest_spring_boot.library.domain.entity.loans.Loans;
import library_rest_spring_boot.library.service.loan.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "3. Loans", description = "Operations related to loans")
@RestController
@RequestMapping("/api/loans")
@AllArgsConstructor
public class LoanResource {

    private final LoanService loanService;

    @Operation(summary = "Get all loans")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public List<Loans> getAllLoans() {
        return loanService.findAll();
    }

    @Operation(summary = "Get a loan by ID")
    @ApiResponse(responseCode = "200", description = "Loan found", content = @Content(schema = @Schema(implementation = Loans.class)))
    @ApiResponse(responseCode = "404", description = "Loan not found")
    @GetMapping("/{id}")
    public ResponseEntity<Loans> getLoanById(@PathVariable Long id) {
        Optional<Loans> loan = loanService.findById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new loan")
    @ApiResponse(responseCode = "200", description = "Loan created", content = @Content(schema = @Schema(implementation = Loans.class)))
    @PostMapping
    public Loans createLoan(@RequestBody Loans loans) {
        return loanService.save(loans);
    }

    @Operation(summary = "Update a loan by ID")
    @ApiResponse(responseCode = "200", description = "Loan updated", content = @Content(schema = @Schema(implementation = Loans.class)))
    @ApiResponse(responseCode = "404", description = "Loan not found")
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

    @Operation(summary = "Delete a loan by ID")
    @ApiResponse(responseCode = "204", description = "Loan deleted")
    @ApiResponse(responseCode = "404", description = "Loan not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
