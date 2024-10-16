package library_rest_spring_boot.library.domain.entity.book;
import library_rest_spring_boot.library.domain.entity.author.AuthorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Date publicationDate;
    private int numberOfPages;
    private AuthorDTO author;

}
