package library_rest_spring_boot.library.domain.entity.author;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String name;
    private Date birthDate;
    private String nationality;

}