package library_rest_spring_boot.library.domain.entity.book;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import library_rest_spring_boot.library.domain.entity.author.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private Date publicationDate;
    private int numberOfPages;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return numberOfPages == books.numberOfPages && Objects.equals(id, books.id) && Objects.equals(title, books.title) && Objects.equals(isbn, books.isbn) && Objects.equals(publicationDate, books.publicationDate) && Objects.equals(author, books.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, publicationDate, numberOfPages, author);
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", numberOfPages=" + numberOfPages +
                ", author=" + author +
                '}';
    }
}
