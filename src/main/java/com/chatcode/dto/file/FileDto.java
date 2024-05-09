package com.chatcode.dto.file;

import com.chatcode.domain.entity.FileEntity;
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
    private long targetId;

    public FileEntity toEntity() {
        return FileEntity.builder()
            .url(url)
            .targetId(targetId)
            .build();
    }

    public static FileDto of(FileEntity fileEntity) {
        return FileDto.builder()
            .id(fileEntity.getId())
            .url(fileEntity.getUrl())
            .targetId(fileEntity.getTargetId())
            .build();
    }

}
