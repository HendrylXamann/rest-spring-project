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
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import library_rest_spring_boot.library.domain.entity.Loans;
import library_rest_spring_boot.library.service.LoanService;
import library_rest_spring_boot.library.repositories.LoansRepository;

public class LoanServiceTest {

    @Mock
    private LoansRepository loansRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Loans loan1 = new Loans();
        Loans loan2 = new Loans();
        when(loansRepository.findAll()).thenReturn(Arrays.asList(loan1, loan2));
        List<Loans> result = loanService.findAll();

        assertEquals(2, result.size());
        verify(loansRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_Found() {
        Loans loan = new Loans();
        when(loansRepository.findById(1L)).thenReturn(Optional.of(loan));
        Optional<Loans> result = loanService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(loan, result.get());
        verify(loansRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(loansRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Loans> result = loanService.findById(1L);

        assertFalse(result.isPresent());
        verify(loansRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Loans loan = new Loans();
        when(loansRepository.save(any(Loans.class))).thenReturn(loan);
        Loans result = loanService.save(loan);

        assertEquals(loan, result);
        verify(loansRepository, times(1)).save(loan);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(loansRepository).deleteById(1L);
        loanService.deleteById(1L);

        verify(loansRepository, times(1)).deleteById(1L);
    }
}
