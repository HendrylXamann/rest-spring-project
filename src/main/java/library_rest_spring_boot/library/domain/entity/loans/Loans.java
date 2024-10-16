package library_rest_spring_boot.library.domain.entity.loans;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import library_rest_spring_boot.library.domain.entity.book.Books;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotNull(message = "Book is mandatory")
    private Books book;

    @NotNull(message = "User ID is mandatory")
    private int userId;

    @NotNull(message = "Loan date is mandatory")
    @FutureOrPresent(message = "Loan date must be in the present or future")
    private Date loanDate;

    @NotNull(message = "Return date is mandatory")
    @FutureOrPresent(message = "Return date must be in the present or future")
    private Date returnDate;

}

