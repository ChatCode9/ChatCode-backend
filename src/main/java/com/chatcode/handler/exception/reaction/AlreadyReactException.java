package com.chatcode.handler.exception.reaction;

public class AlreadyReactException extends RuntimeException{
  public AlreadyReactException(){
    super("You have already raected to this content.");
  }

  public AlreadyReactException(String message){
    super(message);
  }
}
