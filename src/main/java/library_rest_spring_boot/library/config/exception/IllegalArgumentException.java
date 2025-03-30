package library_rest_spring_boot.library.config.exception;

import library_rest_spring_boot.library.config.messages.MessageError;
import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {
    private final MessageError messageError;

    public IllegalArgumentException(MessageError messageError) {
        this.messageError = messageError;
    }
}

