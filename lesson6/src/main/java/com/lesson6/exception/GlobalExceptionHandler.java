package com.lesson6.exception;

import com.lesson6.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logError = LoggerFactory.getLogger("fileLoggerError");

    // Ez a kivételkezelő nem szükséges, ha a UserNotFoundException osztályhoz @ResponseStatus annotáció van beállítva
    /*@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        logError.error("Felhasználó nem található: {}", e.getMessage());
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }*/

    // Validáláshoz szükséges kivételkezelő
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder("A következő mezők érvényesítése nem sikerült:\n");
        // Ha a @NotBlank annotációhoz megadunk alapértelmezett hibaüzenetet a User osztályban
        fieldErrors.forEach(fieldError -> errorMessage.append("* ").append(fieldError.getField()).append(" (").append(fieldError.getDefaultMessage()).append(")\n"));
        errorMessage.delete(errorMessage.length() - 1, errorMessage.length()); // az utolsó listaelem után sortörés ('\n') törlése
        logError.error(errorMessage.toString());
        return errorMessage.toString();
    }
}