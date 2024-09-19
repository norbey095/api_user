package com.emazon.api_user.infraestructure.exceptionhandler;

import com.emazon.api_user.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerUserAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        StringBuilder messageBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messageBuilder.append(errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                messageBuilder.toString().trim(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.USER_ALREADY_EXISTS.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(UserEmailInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleUserEmailInvalidException(UserEmailInvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.USER_EMAIL_CORRECT.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(PhoneNumberinvalidException.class)
    public ResponseEntity<ExceptionResponse> handlePhoneNumberInvalidException(PhoneNumberinvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.USER_PHONE_CORRECT.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(MinorInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleMinorInvalidException(MinorInvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.MINOR_INVALID.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(RolAuxBodegaInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleRolAuxBodegaInvalidException(RolAuxBodegaInvalidException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.ROL_AUX_BODE_EXISTS.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(DocumentInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleDocumentInvalidException(DocumentInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                ExceptionResponseConstants.DOCUMENT_NUMBER_POSITIVE.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException (BadCredentialsException  exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                ExceptionResponseConstants.INCORRECT_DATA.getMessage(),
                HttpStatus.UNAUTHORIZED.toString()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException (AccessDeniedException  exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
                ExceptionResponseConstants.ACCESS_DENE.getMessage(),
                HttpStatus.FORBIDDEN.toString()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionResponse> handleDateTimeParseException(DateTimeParseException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                ExceptionResponseConstants.FORMAT_DATE_INVALID.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }
}
