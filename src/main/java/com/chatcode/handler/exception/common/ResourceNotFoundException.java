package com.chatcode.handler.exception.common;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {
    super("Resource is not found");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}