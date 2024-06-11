package com.chatcode.config.auth.enums;

public enum Status {
    NO_PROFILE(0),
    ACTIVE(1),
    INACTIVE(2);

    private final Integer value;

    Status(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Status of(Integer value) {
        for (Status status : values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}
