package com.chatcode.exception.file;

import static com.chatcode.exception.ExceptionCode.EMPTY_IMAGE_FILE;

public class EmptyImageFileException extends ImageException {
    public EmptyImageFileException() {
        super(EMPTY_IMAGE_FILE);
    }
}