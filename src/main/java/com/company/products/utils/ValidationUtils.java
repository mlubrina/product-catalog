package com.company.products.utils;

import static com.company.products.enums.ValidationError.INVALID_BRAND_ID;
import static com.company.products.enums.ValidationError.INVALID_PRODUCT_ID;
import static com.company.products.enums.ValidationError.MISSING_APPLICATION_DATE;
import static com.company.products.enums.ValidationError.MISSING_BRAND_ID;
import static com.company.products.enums.ValidationError.MISSING_PRODUCT_ID;

import com.company.products.dto.PriceRequest;
import com.company.products.exceptions.ValidationException;

public class ValidationUtils {

  public static void validatePriceRequest(PriceRequest request) throws ValidationException {
    if (request.getApplicationDate() == null) {
      throw new ValidationException(MISSING_APPLICATION_DATE);
    }
    if (request.getProductId() == null) {
      throw new ValidationException(MISSING_PRODUCT_ID);
    }
    if (request.getProductId() <= 0) {
      throw new ValidationException(INVALID_PRODUCT_ID);
    }
    if (request.getBrandId() == null) {
      throw new ValidationException(MISSING_BRAND_ID);
    }
    if (request.getBrandId() <= 0) {
      throw new ValidationException(INVALID_BRAND_ID);
    }
  }
}
