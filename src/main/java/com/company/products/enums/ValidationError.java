package com.company.products.enums;

public enum ValidationError {
  MISSING_APPLICATION_DATE("The applicationDate is missing"),
  MISSING_PRODUCT_ID("The productId is missing"),
  MISSING_BRAND_ID("The brandId is missing"),
  INVALID_APPLICATION_DATE("The applicationDate must be in the format 'yyyy-MM-ddTHH:mm:ss'."),
  INVALID_PRODUCT_ID("The productId must be a positive number."),
  INVALID_BRAND_ID("The brandId must be a positive number.");

  private final String message;

  ValidationError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
