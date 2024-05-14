package com.chatcode.controller.example;

import com.chatcode.controller.example.dto.PostRequestDTO;
import com.chatcode.controller.example.dto.PostResponseDTO;
import com.chatcode.dto.BaseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@RestController
public class TestController {

    @GetMapping("/docs")
    public void getDocs(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }

    /* 주석하는 기본 방법 */
    @GetMapping("/example/post")
    @Operation(summary = "게시글 목록 조회",
            description = "게시글 목록을 조회하는 API (USER 권한 필요)")
    public BaseResponseDto<?> getPostList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {

        List<PostResponseDTO> list = 서비스_getPostList(page, size);
        return new BaseResponseDto<>(200, list, "get 요청 완료");
    }


    /* 기본 주석 + response 코드 주석 */
    @GetMapping("/example/post/{id}")
    @Operation(summary = "게시글 상세 조회",
            description = "게시글 상세를 조회하는 API")
    @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공") // no example
    @ApiResponse(responseCode = "400", description = "잘못된 요청 에러") // no example
    public BaseResponseDto<?> getPostById(@PathVariable Integer id) {

        PostResponseDTO response = new PostResponseDTO();
        response.setTitle("테스트용 제목");
        response.setAuthor("Kim JunHyun");
        return new BaseResponseDto<>(200, response, "get 요청 완료");
    }


    /* 기본 주석 + response 코드 주석 (+ response 예시까지 작성한 경우) */
    @PostMapping("/example/post")
    @Operation(summary = "게시글 등록",
            description = "게시글을 등록하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 등록 성공",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"code\": 200, \"message\": \"post 요청 완료\", \"data\": {\"id\": 1, \"title\": \"제목\", \"content\": \"내용\", \"author\": \"홍길동\"}}"))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"code\": 400, \"message\": \"잘못된 입력입니다.\"}"))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"code\": 500, \"message\": \"서버 처리 중 오류가 발생했습니다.\"}")))
    })
    public BaseResponseDto<?> savePost(@RequestBody PostRequestDTO params) {

        PostResponseDTO response = new PostResponseDTO();
        response.setId(1L);
        response.setTitle(params.getTitle());
        response.setContent(params.getContent());
        response.setAuthor(params.getAuthor());

        return new BaseResponseDto<>(200, response, "post 요청 완료");
    }









    private List<PostResponseDTO> 서비스_getPostList(Integer page, Integer size) {
        List<PostResponseDTO> list = new ArrayList<>();
        for (Integer i = (page - 1) * size; i < (page - 1) * size + size; i++) {
            PostResponseDTO response = new PostResponseDTO();
            response.setId(i.longValue());
            response.setTitle("제목" + i);
            response.setContent("내용" + i);
            response.setAuthor("홍길동" + i);
            list.add(response);
        }
        return list;
    }
}
