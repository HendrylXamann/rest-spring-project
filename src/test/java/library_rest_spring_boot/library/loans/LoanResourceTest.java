package library_rest_spring_boot.library.loans;
import java.util.List;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import library_rest_spring_boot.library.domain.entity.loans.Loans;
import library_rest_spring_boot.library.service.LoanService;
import library_rest_spring_boot.library.resources.loans.LoanResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class LoanResourceTest {

    private MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanResource loanResource;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanResource).build();
    }

    @Test
    public void testGetAllLoans() throws Exception {
        Loans loan1 = new Loans();
        Loans loan2 = new Loans();
        given(loanService.findAll()).willReturn(Arrays.asList(loan1, loan2));

        mockMvc.perform(get("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetLoanByIdFound() throws Exception {
        Loans loan = new Loans();
        given(loanService.findById(1L)).willReturn(Optional.of(loan));

        mockMvc.perform(get("/api/loans/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLoanByIdNotFound() throws Exception {
        given(loanService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/loans/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateLoan() throws Exception {
        Loans loan = new Loans();
        given(loanService.save(any(Loans.class))).willReturn(loan);

        mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateLoanFound() throws Exception {
        Loans existingLoan = new Loans(); // Configure existingLoan with test data
        given(loanService.findById(1L)).willReturn(Optional.of(existingLoan));
        given(loanService.save(any(Loans.class))).willReturn(existingLoan);

        mockMvc.perform(put("/api/loans/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateLoanNotFound() throws Exception {
        given(loanService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(put("/api/loans/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteLoan() throws Exception {
        doNothing().when(loanService).deleteById(1L);

        mockMvc.perform(delete("/api/loans/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
