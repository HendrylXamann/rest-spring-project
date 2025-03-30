package library_rest_spring_boot.library.domain.entity.loans;
import jakarta.persistence.*;
import library_rest_spring_boot.library.domain.entity.book.Books;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "loans")
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
    private Books book;
    private Integer userId;
    private Date loanDate;
    private Date returnDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loans loans = (Loans) o;
        return userId == loans.userId && Objects.equals(id, loans.id) && Objects.equals(book, loans.book) && Objects.equals(loanDate, loans.loanDate) && Objects.equals(returnDate, loans.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, userId, loanDate, returnDate);
    }

    @Override
    public String toString() {
        return "Loans{" +
                "id=" + id +
                ", book=" + book +
                ", userId=" + userId +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

