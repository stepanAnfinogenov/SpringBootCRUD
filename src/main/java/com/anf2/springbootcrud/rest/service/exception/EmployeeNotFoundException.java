package com.anf2.springbootcrud.rest.service.exception;

public class EmployeeNotFoundException extends Exception {
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
