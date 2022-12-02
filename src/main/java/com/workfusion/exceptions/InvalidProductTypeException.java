package com.workfusion.exceptions;

public class InvalidProductTypeException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidProductTypeException(String msg) {
    super(msg);
  }
}
