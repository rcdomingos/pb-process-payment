package com.compass.pb.projects.processpayment.exception.handler;

import com.compass.pb.projects.processpayment.domain.DefaultMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DefaultMessageResponse> handleValidationExceptions(ConstraintViolationException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errorMessage = new ArrayList<>();
        if (!violations.isEmpty()) {
            violations.forEach(violation -> errorMessage.add(violation.getInvalidValue() + ": " + violation.getMessage()));
        } else {
            errorMessage.add("ConstraintViolationException occured.");
        }
        return ResponseEntity.status(status).body(new DefaultMessageResponse(String.valueOf(status.value()), errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultMessageResponse> handleValidationExceptions(MethodArgumentNotValidException e,
                                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessage = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(ex -> {
            String msgError = "O campo '" + ex.getField() + "' " + ex.getDefaultMessage();
            errorMessage.add(msgError);
        });

        DefaultMessageResponse err = new DefaultMessageResponse(String.valueOf(status.value()), errorMessage);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DefaultMessageResponse> handleAccessDeniedException(BadCredentialsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMenssagem = e.getMessage().replace("Usuário", "ClientId").replace("senha", "apikey");
        DefaultMessageResponse err = new DefaultMessageResponse(String.valueOf(status.value()), errorMenssagem);
        return ResponseEntity.status(status).body(err);
    }

}
