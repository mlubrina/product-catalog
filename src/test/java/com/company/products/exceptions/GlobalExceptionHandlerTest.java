package com.company.products.exceptions;

import com.company.products.enums.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Global Exception Handler Test")
public class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler handler;

  @BeforeEach
  public void setUp() {
    handler = new GlobalExceptionHandler();
  }

  @Test
  @DisplayName("Test should validate exception handling")
  public void testHandleValidationException() {
    ValidationError errorMessage = ValidationError.INVALID_PRODUCT_ID;
    ValidationException exception = new ValidationException(errorMessage);

    ResponseEntity<String> responseEntity = handler.handleValidationException(exception);

    assertEquals(errorMessage.getMessage(), responseEntity.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  @DisplayName("Test should bind exception handling")
  public void testHandleBindException() {
    BindException ex = new BindException(new Object(), "testObjectName");
    ex.addError(createFieldError("applicationDate"));
    ex.addError(createFieldError("productId"));
    ex.addError(createFieldError("brandId"));

    ResponseEntity<List<String>> responseEntity = handler.handleBindException(ex);

    assertEquals(3, responseEntity.getBody().size());
    assertEquals(ValidationError.INVALID_APPLICATION_DATE.getMessage(),
        responseEntity.getBody().get(0));
    assertEquals(ValidationError.INVALID_PRODUCT_ID.getMessage(), responseEntity.getBody().get(1));
    assertEquals(ValidationError.INVALID_BRAND_ID.getMessage(), responseEntity.getBody().get(2));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  private FieldError createFieldError(String field) {
    return new FieldError("objectName", field, "defaultMessage");
  }
}
