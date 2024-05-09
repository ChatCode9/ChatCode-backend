package com.chatcode.service;

import com.chatcode.domain.entity.FileEntity;
import com.chatcode.dto.file.FileDto;
import com.chatcode.repository.file.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final S3Service s3Service;

    public void saveFile(FileDto fileDto) {
        fileRepository.save(fileDto.toEntity());
    }

    public List<FileEntity> createFileList(List<MultipartFile> fileList, long targetId) throws IOException {
        List<FileEntity> fileEntityList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String url = s3Service.upload(file, "article");
            fileEntityList.add(FileEntity.builder()
                    .url(url)
                    .targetId(targetId)
                    .build());
        }
        return fileEntityList;
    }

}
