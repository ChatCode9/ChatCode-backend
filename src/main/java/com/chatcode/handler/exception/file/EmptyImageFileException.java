package com.chatcode.handler.exception.file;

import static com.chatcode.handler.exception.ExceptionCode.EMPTY_IMAGE_FILE;

public class EmptyImageFileException extends ImageException {
    public EmptyImageFileException() {
        super(EMPTY_IMAGE_FILE);
    }
}