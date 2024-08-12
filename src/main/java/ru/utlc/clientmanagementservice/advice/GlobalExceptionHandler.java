package ru.utlc.clientmanagementservice.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.utlc.clientmanagementservice.exception.BusinessTypeCreationException;
import ru.utlc.clientmanagementservice.exception.ClientCreationException;
import ru.utlc.clientmanagementservice.exception.ClientStatusCreationException;
import ru.utlc.clientmanagementservice.exception.IndustryTypeCreationException;
import ru.utlc.clientmanagementservice.response.Response;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> messageSource.getMessage(error.getDefaultMessage(), null, LocaleContextHolder.getLocale()))
                .toList();
        Response response = new Response(errorMessages, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Response> handleBindException(BindException ex) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> messageSource.getMessage(error.getDefaultMessage(), null, LocaleContextHolder.getLocale()))
                .toList();
        Response response = new Response(errorMessages, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<Response> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage;

        if (isForeignKeyConstraintViolation(ex)) {
            errorMessage = messageSource.getMessage("error.database.foreignKeyConstraintViolation", null, LocaleContextHolder.getLocale());
        } else if (isUniqueConstraintViolation(ex)) {
            errorMessage = messageSource.getMessage("error.database.uniqueConstraintViolation", null, LocaleContextHolder.getLocale());
        } else {
            errorMessage = messageSource.getMessage("error.database.genericIntegrityViolation", null, LocaleContextHolder.getLocale());
        }
        return ResponseEntity.status(CONFLICT).body(new Response(errorMessage));
    }

    private boolean isForeignKeyConstraintViolation(DataIntegrityViolationException ex) {
        return ex.getMessage() != null && ex.getMessage().contains("violates foreign key constraint");
    }

    private boolean isUniqueConstraintViolation(DataIntegrityViolationException ex) {
        return ex.getMessage() != null && ex.getMessage().contains("violates unique constraint");
    }

    @ExceptionHandler(ClientCreationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Response> handleEntityCreationException(ClientCreationException ex) {
        String errorMessage = messageSource.getMessage("error.entity.client.creation", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(BAD_REQUEST).body(new Response(errorMessage));
    }

    @ExceptionHandler(ClientStatusCreationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Response> handleEntityCreationException(ClientStatusCreationException ex) {
        String errorMessage = messageSource.getMessage("error.entity.clientStatus.creation", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(BAD_REQUEST).body(new Response(errorMessage));
    }

    @ExceptionHandler(BusinessTypeCreationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Response> handleEntityCreationException(BusinessTypeCreationException ex) {
        String errorMessage = messageSource.getMessage("error.entity.businessType.creation", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(BAD_REQUEST).body(new Response(errorMessage));
    }

    @ExceptionHandler(IndustryTypeCreationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Response> handleEntityCreationException(IndustryTypeCreationException ex) {
        String errorMessage = messageSource.getMessage("error.entity.industryType.creation", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(BAD_REQUEST).body(new Response(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println(Arrays.toString(ex.getStackTrace()));

        String errorMessage = messageSource.getMessage("error.general", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Response(errorMessage));
    }
}
