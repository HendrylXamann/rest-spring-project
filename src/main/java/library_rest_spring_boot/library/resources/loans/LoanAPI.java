package library_rest_spring_boot.library.resources.loans;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import library_rest_spring_boot.library.domain.entity.loans.Loans;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "3. Loans", description = "Operations related to loans")
@RequestMapping("/api/loans")
public interface LoanAPI {

    @Operation(summary = "Get all loans")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    List<Loans> getAllLoans();

    @Operation(summary = "Get a loan by ID")
    @ApiResponse(responseCode = "200", description = "Loan found", content = @Content(schema = @Schema(implementation = Loans.class)))
    @ApiResponse(responseCode = "404", description = "Loan not found")
    @GetMapping("/{id}")
    ResponseEntity<Loans> getLoanById(@PathVariable Long id);

    @Operation(summary = "Create a new loan")
    @ApiResponse(responseCode = "200", description = "Loan created", content = @Content(schema = @Schema(implementation = Loans.class)))
    @PostMapping
    Loans createLoan(@RequestBody Loans loans);

    @Operation(summary = "Update a loan by ID")
    @ApiResponse(responseCode = "200", description = "Loan updated", content = @Content(schema = @Schema(implementation = Loans.class)))
    @ApiResponse(responseCode = "404", description = "Loan not found")
    @PutMapping("/{id}")
    ResponseEntity<Loans> updateLoan(@PathVariable Long id, @RequestBody Loans loanDetails);

    @Operation(summary = "Delete a loan by ID")
    @ApiResponse(responseCode = "204", description = "Loan deleted")
    @ApiResponse(responseCode = "404", description = "Loan not found")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteLoan(@PathVariable Long id);
}
