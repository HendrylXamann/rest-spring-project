package library_rest_spring_boot.library.domain.entity.author.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
public class AuthorDTO {
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Birth date cannot be null")
    private Date birthDate;
    @NotNull(message = "Nationality cannot be null")
    private String nationality;

    public AuthorDTO(){
    }

    public AuthorDTO(String name, Date birthDate, String nationality){
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }
}