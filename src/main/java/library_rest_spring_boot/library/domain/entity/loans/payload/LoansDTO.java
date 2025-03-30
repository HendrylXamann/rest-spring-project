package library_rest_spring_boot.library.domain.entity.loans.payload;

import library_rest_spring_boot.library.domain.entity.book.Books;
import lombok.Data;
import java.util.Date;

@Data
public class LoansDTO {
    private Books book;
    private Integer userId;
    private Date loanDate;
    private Date returnDate;
}
