package library_rest_spring_boot.library.domain.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Birth date is mandatory")
    private Date birthDate;
    @NotBlank(message = "Nationality is mandatory")
    private String nationality;

    public Author() {
    }

    public Author(String name, Date birthDate, String nationality) {
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
// só validar se o nome não for nulo | ver se é necessario isso
    public boolean isValid() {
        return name != null;
    }
}
