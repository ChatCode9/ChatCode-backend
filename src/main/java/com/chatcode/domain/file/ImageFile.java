package com.chatcode.domain.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Setter
public class ImageFile implements MultipartFile {

    private static final String EXTENSION_DELIMITER = ".";

    private byte[] bytes;
    private String contentType;
    private String UUIDFileName;

    @JsonCreator
    public ImageFile(@JsonProperty("base64String") String base64String) {
        DataUrl dataUrl = new DataUrl(base64String);

        this.bytes = Base64.getDecoder().decode(dataUrl.getBase64Data());
        this.contentType = "image/" + dataUrl.getFileExtension();
        this.UUIDFileName = generateUUIDFileName(dataUrl);
    }

    private String generateUUIDFileName(final DataUrl dataUrl) {
        return UUID.randomUUID() + EXTENSION_DELIMITER + dataUrl.getFileExtension();
    }

    public long getSize() {
        return this.bytes.length;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.bytes);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getOriginalFilename() {
        return "";
    }
}
