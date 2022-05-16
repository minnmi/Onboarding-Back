package br.com.totemti.onboardingback.configurations;

import br.com.totemti.onboardingback.controllers.dtos.ErrorResponse;
import br.com.totemti.onboardingback.exceptions.DomainException;
import br.com.totemti.onboardingback.exceptions.EntityNotFoundException;
import com.itextpdf.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            final DomainException exception,
            final HttpStatus httpStatus,
            final WebRequest webRequest
    ) {
        final var path = ((ServletWebRequest) webRequest).getRequest().getRequestURI();
        this.log.error("Context Path: {}", path);
        exception.printStackTrace();
        return ResponseEntity.status(httpStatus).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        httpStatus.value(),
                        httpStatus,
                        exception.getMessage(),
                        path
                )
        );
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleIOException(
            final IOException exception,
            final WebRequest webRequest
    ) {
        this.log.error("Input/Output exception occurred: {}", exception.getMessage());
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            final String message,
            final HttpStatus httpStatus,
            final WebRequest webRequest
    ) {
        final var path = ((ServletWebRequest) webRequest).getRequest().getRequestURI();
        this.log.error("Context Path: {}", path);
        return ResponseEntity.status(httpStatus).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        httpStatus.value(),
                        httpStatus,
                        message,
                        path
                )
        );
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleNullPointerException(
            final NullPointerException exception,
            final WebRequest webRequest
    ) {
        this.log.error("NullpointerException occurred: {}", exception.getMessage());
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            final EntityNotFoundException exception,
            final WebRequest webRequest
    ) {
        this.log.error("Failed to fetch element: {}", exception.getMessage());
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception,
                HttpStatus.NOT_FOUND,
                webRequest
        );
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleDomainException(
            final DomainException exception,
            final WebRequest webRequest
    ) {
        this.log.error("A domain exception occurred", exception);
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception,
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            final Exception exception,
            final WebRequest webRequest
    ) {
        this.log.error("Unknown error occurred", exception);
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            final IllegalArgumentException exception,
            final WebRequest webRequest
    ) {
        this.log.error("Unknown error occurred", exception);
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(
            final IllegalStateException exception,
            final WebRequest webRequest
    ) {
        this.log.error("Unknown error occurred", exception);
        exception.printStackTrace();
        return this.buildErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final var errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Validation error. Check 'errors' field for details.",
                request.getContextPath()
        );

        for (final var fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}
