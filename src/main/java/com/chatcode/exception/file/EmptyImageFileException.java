package com.chatcode.exception.file;

public class EmptyImageFileException extends RuntimeException{
    public EmptyImageFileException(){
        super("이미지 파일이 존재하지 않습니다.");
    }

    public EmptyImageFileException(String message){
        super(message);
    }
}