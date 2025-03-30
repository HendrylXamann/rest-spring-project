package library_rest_spring_boot.library.config.messages;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize(using = MessageErrorSerializer.class)
public enum MessageError {
    NOT_FOUND_ID(404, "NOT_FOUND_ID", "Not found by id"),
    AUTHOR_NOT_FOUND(404, "AUTHOR_NOT_FOUND", "Author(s) not found"),
    BOOK_RELATED_AUTHOR_NOT_FOUND(404, "BOOK_RELATED_AUTHOR_NOT_FOUND", "Book(s) related to author not found"),
    AUTHOR_WITH_BOOKS_HAS_LOANS(400, "AUTHOR_WITH_BOOKS_HAS_LOANS", "Cannot delete author with borrowed books"),
    AUTHOR_ALREADY_EXISTS(400, "AUTHOR_ALREADY_EXISTS", "Author already exists");
    private final int status;
    private final String code;
    private final String message;

    MessageError(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

