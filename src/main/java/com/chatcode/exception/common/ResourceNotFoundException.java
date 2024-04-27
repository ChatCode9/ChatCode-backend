package com.chatcode.exception.common;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {
    super("Resource is not found");
  }
}