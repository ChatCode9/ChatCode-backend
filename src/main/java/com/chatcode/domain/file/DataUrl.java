package com.chatcode.domain.file;

import com.chatcode.handler.exception.file.EmptyImageFileException;
import lombok.Getter;


/**
 * Data URLs 형태
 * data:[<mediatype>][;base64],<data>
 */
@Getter
public class DataUrl {
    private final String metadata;
    private final String base64Data;
    private final String fileExtension;
    private static final String IMAGE_MIMETYPE_PREFIX = "data:image/";

    public DataUrl(String dataUrl) {
        validateDataUrl(dataUrl);

        String[] parts = dataUrl.split(",", 2);
        this.metadata = parts[0];
        this.base64Data = parts[1];
        this.fileExtension = setFileExtension(parts[0]);
    }

    private static void validateDataUrl(String dataUrl) {
        String[] parts = dataUrl.split(",", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid Data URL format");
        }

        if(parts[0].isBlank() || parts[1].isBlank()) {
            throw new EmptyImageFileException();
        }

        if(!dataUrl.startsWith(IMAGE_MIMETYPE_PREFIX)){
            throw new IllegalArgumentException("Not Image MIME Type");
        }
    }

    private static String setFileExtension(String metadata) {
        if (metadata.startsWith(IMAGE_MIMETYPE_PREFIX)) {
            return metadata.substring(IMAGE_MIMETYPE_PREFIX.length(), metadata.indexOf(';'));
        } {
            throw new IllegalArgumentException("Unsupported image type");
        }
    }
}
