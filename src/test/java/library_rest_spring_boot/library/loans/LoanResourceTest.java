package library_rest_spring_boot.library.loans;
import java.util.List;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import library_rest_spring_boot.library.domain.entity.Loans;
import library_rest_spring_boot.library.service.LoanService;
import library_rest_spring_boot.library.resources.loans.LoanResource;

@ExtendWith(MockitoExtension.class)
public class LoanResourceTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanResource loanResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLoans() {
        Loans loan1 = new Loans();
        Loans loan2 = new Loans();
        when(loanService.findAll()).thenReturn(Arrays.asList(loan1, loan2));
        List<Loans> result = loanResource.getAllLoans();

        assertEquals(2, result.size());
        verify(loanService, times(1)).findAll();
    }

    @Test
    public void testGetLoanById_Found() {
        Loans loan = new Loans();
        when(loanService.findById(1L)).thenReturn(Optional.of(loan));
        ResponseEntity<Loans> response = loanResource.getLoanById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loan, response.getBody());
        verify(loanService, times(1)).findById(1L);
    }

    @Test
    public void testGetLoanById_NotFound() {
        when(loanService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Loans> response = loanResource.getLoanById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(loanService, times(1)).findById(1L);
    }

    @Test
    public void testCreateLoan() {
        Loans loan = new Loans();
        when(loanService.save(any(Loans.class))).thenReturn(loan);
        Loans result = loanResource.createLoan(loan);

        assertEquals(loan, result);
        verify(loanService, times(1)).save(loan);
    }

    @Test
    public void testUpdateLoan_Found() {
        Loans existingLoan = new Loans();
        Loans updatedLoan = new Loans();
        when(loanService.findById(1L)).thenReturn(Optional.of(existingLoan));
        when(loanService.save(any(Loans.class))).thenReturn(updatedLoan);
        ResponseEntity<Loans> response = loanResource.updateLoan(1L, updatedLoan);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedLoan, response.getBody());
        verify(loanService, times(1)).findById(1L);
        verify(loanService, times(1)).save(existingLoan);
    }

    @Test
    public void testUpdateLoan_NotFound() {
        Loans updatedLoan = new Loans();
        when(loanService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Loans> response = loanResource.updateLoan(1L, updatedLoan);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(loanService, times(1)).findById(1L);
        verify(loanService, never()).save(any(Loans.class));
    }

    @Test
    public void testDeleteLoan() {
        doNothing().when(loanService).deleteById(1L);
        ResponseEntity<Void> response = loanResource.deleteLoan(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(loanService, times(1)).deleteById(1L);
    }
}
