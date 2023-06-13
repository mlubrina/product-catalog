package com.company.products.exceptions;

import com.company.products.enums.ValidationError;

public class ValidationException extends RuntimeException {
  private final ValidationError errorCode;

  public ValidationException(ValidationError errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode.getMessage();
  }
}
