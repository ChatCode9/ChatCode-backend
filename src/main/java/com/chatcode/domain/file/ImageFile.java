package com.chatcode.domain.file;

import com.chatcode.exception.file.EmptyImageFileException;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.springframework.util.StringUtils.getFilenameExtension;

@Getter
public class ImageFile {

    private static final String EXTENSION_DELIMITER = ".";

    private MultipartFile file;
    private String UUIDFileName;

    public ImageFile(final MultipartFile file) {
        validateNullImage(file);

        this.file = file;
        this.UUIDFileName = generateUUIDFileName(file);
    }

    private void validateNullImage(final MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyImageFileException();
        }
    }

    private String generateUUIDFileName(final MultipartFile file) {
        final String name = file.getOriginalFilename();

        return UUID.randomUUID() + EXTENSION_DELIMITER + getFilenameExtension(name);
    }

    public String getContentType() {
        return this.file.getContentType();
    }

    public long getSize() {
        return this.file.getSize();
    }

    public InputStream getInputStream() throws IOException {
        return this.file.getInputStream();
    }
}
