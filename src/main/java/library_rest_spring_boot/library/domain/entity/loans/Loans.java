package library_rest_spring_boot.library.domain.entity.loans;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import library_rest_spring_boot.library.domain.entity.book.Books;

import java.util.Date;

@Entity
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
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

    public Loans() {}

    public Loans(Books book, int userId, Date loanDate, Date returnDate) {
        this.book = book;
        this.userId = userId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Books getBook() {
        return book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

