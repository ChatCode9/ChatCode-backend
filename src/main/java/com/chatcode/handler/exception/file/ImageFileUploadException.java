package com.chatcode.handler.exception.file;

public class ImageFileUploadException extends RuntimeException{
    public ImageFileUploadException(){
        super("이미지 파일 업로드 중 에러가 발생했습니다.");
    }

    public ImageFileUploadException(String message){
        super(message);
    }
}