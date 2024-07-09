package com.chatcode.exception.file;

import static com.chatcode.exception.ExceptionCode.FAIL_IMAGE_UPLOAD;

public class ImageFileUploadException extends ImageException {
    public ImageFileUploadException() {
        super(FAIL_IMAGE_UPLOAD);
    }

    public ImageFileUploadException(String message) {
        super(FAIL_IMAGE_UPLOAD, message);
    }
}