package de.allcom.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
    private final HttpStatus status;

    public NotFoundException(HttpStatus httpStatus, String message) {
        super(message);
        this.status = httpStatus;
    }
}
