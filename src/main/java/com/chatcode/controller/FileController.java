package com.chatcode.controller;

import com.chatcode.dto.file.FileDto;
import com.chatcode.service.FileService;
import com.chatcode.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class FileController {

    private final S3Service s3Service;
    private final FileService fileService;

    @GetMapping("/file")
    public String dispWrite() {

        return "file";
    }

    @PostMapping("/file")
    public String execWrite(FileDto fileDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(file, "test");
        fileDto.setUrl(imgPath);

        fileService.saveFile(fileDto);

        return "redirect:/";
    }
}
