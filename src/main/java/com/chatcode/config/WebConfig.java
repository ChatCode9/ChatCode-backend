package com.chatcode.config;

import com.chatcode.domain.file.Base64ToMultipartFileConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final Base64ToMultipartFileConverter base64ToMultipartFileConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(base64ToMultipartFileConverter);
    }
}