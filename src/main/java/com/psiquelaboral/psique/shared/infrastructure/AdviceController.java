package com.psiquelaboral.psique.shared.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> generalError(Exception e, WebRequest request) {
        ProblemDetail response = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseStatusException responseStatusException(ResponseStatusException e, WebRequest request) {
        return e;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> validationsHandler(MethodArgumentNotValidException e, WebRequest request) {
        ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetails.setTitle("Invalid fields on body");
        problemDetails.setDetail("Check the field errors on this response to see the details of the errors");

        List<ErrorDetails> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ErrorDetails(error.getField(), error.getDefaultMessage()))
                .toList();

        problemDetails.setProperty("errors", errors);

        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    public record ErrorDetails(String field, String error) {
    }
}
