package com.company.products.exceptions;

import com.company.products.enums.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Global Exception Handler Test")
class GlobalExceptionHandlerTest {

  @Test
  @DisplayName("Test should Handle ValidationException")
  void handleValidationException() {
    ValidationException ex = new ValidationException(ValidationError.INVALID_APPLICATION_DATE);

    GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    ResponseEntity<String> response = exceptionHandler.handleValidationException(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(ValidationError.INVALID_APPLICATION_DATE.getMessage(), response.getBody());
  }

  @Test
  @DisplayName("Test should Handle BindException")
  void handleBindException() {
    Object boundForm = new Object();

    BindException ex = new BindException(boundForm, null);

    GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    ResponseEntity<List<String>> response = exceptionHandler.handleBindException(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(Arrays.asList(), response.getBody());
  }

}
