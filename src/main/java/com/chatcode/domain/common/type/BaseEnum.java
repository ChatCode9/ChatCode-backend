package com.chatcode.domain.common.type;

public interface BaseEnum<T extends Enum<T>> {

    String name();

    static <T extends Enum<T> & BaseEnum<T>> T getEnum(Class<T> clazz, String name) {
        for (T e : clazz.getEnumConstants()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown enum value:" + name);
    }
}
