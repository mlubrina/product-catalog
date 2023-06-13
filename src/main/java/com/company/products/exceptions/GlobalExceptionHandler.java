package com.company.products.exceptions;

import static com.company.products.enums.ValidationError.INVALID_APPLICATION_DATE;
import static com.company.products.enums.ValidationError.INVALID_BRAND_ID;
import static com.company.products.enums.ValidationError.INVALID_PRODUCT_ID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> handleValidationException(ValidationException ex) {
    logger.error("Validation exception occurred: {}", ex.getMessage());
    return new ResponseEntity<>(ex.getErrorCode(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<List<String>> handleBindException(BindException ex) {
    List<String> errors = ex.getBindingResult().getAllErrors()
        .stream()
        .map(error -> {
          FieldError fieldError = (FieldError) error;
          switch (fieldError.getField()) {
            case "applicationDate":
              return INVALID_APPLICATION_DATE.getMessage();
            case "productId":
              return INVALID_PRODUCT_ID.getMessage();
            case "brandId":
              return INVALID_BRAND_ID.getMessage();
            default:
              return null;
          }
        })
        .filter(message -> message != null)
        .collect(Collectors.toList());

    logger.error("Bind exception occurred: {}", errors);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

}
