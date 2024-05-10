package com.chatcode.service;

import com.chatcode.domain.file.ImageFile;
import com.chatcode.dto.file.FileDto;
import com.chatcode.dto.file.FileRequestDto;
import com.chatcode.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final S3Service s3Service;

    private void saveFile(FileDto fileDto) {
        fileRepository.save(fileDto.toEntity());
    }

    public String uploadImages(final FileRequestDto dto) {
        String imgPath = s3Service.uploadImage(new ImageFile(dto.getBase64File()));

        saveFile(FileDto.builder().url(imgPath).targetId(dto.getTargetId()).build());

        return imgPath;
    }
}
