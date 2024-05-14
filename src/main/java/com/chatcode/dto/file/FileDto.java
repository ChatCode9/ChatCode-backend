package com.chatcode.dto.file;

import com.chatcode.domain.entity.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {

    private long id;
    private String url;
    private Long targetId;

    public File toEntity() {
        return File.builder()
            .url(url)
            .targetId(targetId)
            .build();
    }
}
