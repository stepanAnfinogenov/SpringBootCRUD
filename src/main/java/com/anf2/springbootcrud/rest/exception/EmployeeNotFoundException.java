package com.anf2.springbootcrud.rest.exception;

public class EmployeeNotFoundException extends Exception {
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
