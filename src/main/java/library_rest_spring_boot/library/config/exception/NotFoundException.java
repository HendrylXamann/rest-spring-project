package library_rest_spring_boot.library.config.exception;
import library_rest_spring_boot.library.config.messages.MessageError;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final MessageError messageError;

    public NotFoundException(MessageError messageError) {
        this.messageError = messageError;
    }

}

