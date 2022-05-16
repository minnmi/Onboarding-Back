package br.com.totemti.onboardingback.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private final LocalDateTime at;
    private final int code;
    private final HttpStatus status;
    private final String message;
    private final String path;
    private List<ValidationError> errors;

    public ErrorResponse(LocalDateTime at, int code, HttpStatus status, String message, String path) {
        this.at = at;
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    private static String format(final String message) {
        final var bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        return bundle.getString(message);
    }

    public void addValidationError(final String field, final String message) {
        if (Objects.isNull(this.errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(new ValidationError(field, format(message)));
    }

    @Getter
    @AllArgsConstructor
    private class ValidationError {
        private final String field;

        private final String message;

    }
}