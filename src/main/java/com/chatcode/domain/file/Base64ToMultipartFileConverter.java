package com.chatcode.domain.file;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Base64ToMultipartFileConverter implements Converter<String, ImageFile> {

    @Override
    public ImageFile convert(@NonNull String source) {
        return new ImageFile(source);
    }
}
