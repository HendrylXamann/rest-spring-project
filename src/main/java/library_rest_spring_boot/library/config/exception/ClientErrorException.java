package library_rest_spring_boot.library.config.exception;

import library_rest_spring_boot.library.config.messages.MessageError;
import lombok.Getter;

@Getter
public class ClientErrorException extends RuntimeException {
    private final MessageError messageError;

    public ClientErrorException(MessageError messageError) {
        this.messageError = messageError;
    }
}
