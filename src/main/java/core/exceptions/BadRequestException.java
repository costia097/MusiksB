package core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
