package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpinionController {
    @GetMapping("")
    public ResponseEntity<BaseResponseDto<>> getOpinions(){
//        todo
    }
}
