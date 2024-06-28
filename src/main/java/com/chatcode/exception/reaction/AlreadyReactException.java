package com.chatcode.exception.reaction;

import static com.chatcode.exception.ExceptionCode.ALREADY_REACTED;


public class AlreadyReactException extends ReactException {
    public AlreadyReactException() {
        super(ALREADY_REACTED);
    }

    public AlreadyReactException(String message) {
        super(ALREADY_REACTED, message);
    }
}
