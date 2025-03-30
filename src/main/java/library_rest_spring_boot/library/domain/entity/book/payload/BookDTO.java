package library_rest_spring_boot.library.domain.entity.book.payload;
import library_rest_spring_boot.library.domain.entity.author.payload.AuthorDTO;
import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {
    private String title;
    private String isbn;
    private Date publicationDate;
    private int numberOfPages;
    private AuthorDTO author;

}
