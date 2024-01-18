package de.allcom.exceptions;

import de.allcom.dto.StandardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CopyPhotoExceptionHandler {

    @ExceptionHandler(value = CopyPhotoException.class)
    public ResponseEntity<StandardResponseDto> handleRestException(CopyPhotoException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(StandardResponseDto.builder()
                        .message(e.getMessage())
                        .build());
    }
}
