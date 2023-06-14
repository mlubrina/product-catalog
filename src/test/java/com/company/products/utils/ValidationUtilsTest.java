package com.company.products.utils;

import com.company.products.dto.PriceRequest;
import com.company.products.enums.ValidationError;
import com.company.products.exceptions.ValidationException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Validation Util Test")
public class ValidationUtilsTest {

  @Test
  @DisplayName("Test should check Valid PriceRequest")
  void validatePriceRequest_ValidRequest() throws ValidationException {
    PriceRequest request = new PriceRequest();
    request.setApplicationDate(LocalDateTime.now());
    request.setProductId(123L);
    request.setBrandId(456L);

    ValidationUtils.validatePriceRequest(request);
  }

  @Test
  @DisplayName("Test should check Missing Application Date")
  void validatePriceRequest_MissingApplicationDate() {
    PriceRequest request = new PriceRequest();
    request.setProductId(123L);
    request.setBrandId(456L);

    assertThrows(ValidationException.class,
        () -> ValidationUtils.validatePriceRequest(request),
        ValidationError.MISSING_APPLICATION_DATE.getMessage());
  }

  @Test
  @DisplayName("Test should check Missing Product ID")
  void validatePriceRequest_MissingProductId() {
    PriceRequest request = new PriceRequest();
    request.setApplicationDate(LocalDateTime.now());
    request.setBrandId(456L);

    assertThrows(ValidationException.class,
        () -> ValidationUtils.validatePriceRequest(request),
        ValidationError.MISSING_PRODUCT_ID.getMessage());
  }

  @Test
  @DisplayName("Test should check Invalid Product ID")
  void validatePriceRequest_InvalidProductId() {
    PriceRequest request = new PriceRequest();
    request.setApplicationDate(LocalDateTime.now());
    request.setProductId(-123L);
    request.setBrandId(456L);

    assertThrows(ValidationException.class,
        () -> ValidationUtils.validatePriceRequest(request),
        ValidationError.INVALID_PRODUCT_ID.getMessage());
  }

  @Test
  @DisplayName("Test should check Missing Brand ID")
  void validatePriceRequest_MissingBrandId() {
    PriceRequest request = new PriceRequest();
    request.setApplicationDate(LocalDateTime.now());
    request.setProductId(123L);

    assertThrows(ValidationException.class,
        () -> ValidationUtils.validatePriceRequest(request),
        ValidationError.MISSING_BRAND_ID.getMessage());
  }

  @Test
  @DisplayName("Test should check Invalid Brand ID")
  void validatePriceRequest_InvalidBrandId() {
    PriceRequest request = new PriceRequest();
    request.setApplicationDate(LocalDateTime.now());
    request.setProductId(123L);
    request.setBrandId(-456L);

    assertThrows(ValidationException.class,
        () -> ValidationUtils.validatePriceRequest(request),
        ValidationError.INVALID_BRAND_ID.getMessage());
  }
}
