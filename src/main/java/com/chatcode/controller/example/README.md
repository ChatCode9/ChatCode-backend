## Swagger 작성
### SwaggerConfig
SwaggerConfig 클래스는 Swagger 설정을 위한 클래스.
혹시나 Swagger 문서에서 path를 그룹화 하고 싶다면 GroupedOpenApi 빈을 생성하여 그룹화 할 수 있다.
```java
    @Bean
    public GroupedOpenApi exampleOpenApi() {
        return GroupedOpenApi.builder()
                .group("example")
                .pathsToMatch("/example/**")
                .build();
    }
```

### RequestDTO 작성
RequestDTO 클래스는 POST, PUT 요청시 Request Body에 들어갈 데이터를 정의한 클래스.
해당 클래스에서 example을 정의하고자 한다면, @Scheme 어노테이션을 사용하여 정의할 수 있다.
```java
@Schema
public class PostRequestDTO {
    @Schema(description = "제목", example = "제목")
    private String title;
    @Schema(description = "내용", example = "내용")
    private String content;
    @Schema(description = "작성자", example = "홍길동")
    private String author;
    @Schema(description = "비밀번호", example = "비밀번호")
    private String password;
}
```

### ResponseDTO 작성
ResponseDTO 클래스는 GET, POST, PUT 요청시 Response Body에 들어갈 데이터를 정의한 클래스.
해당 클래스에서 example을 정의하고자 한다면, @Scheme 어노테이션을 사용하여 정의할 수 있다.
```java
@Schema
public class PostResponseDTO {
    @Schema(description = "게시글 번호", example = "1")
    private Long id;
    @Schema(description = "제목", example = "제목")
    private String title;
    @Schema(description = "내용", example = "내용")
    private String content;
    @Schema(description = "작성자", example = "홍길동")
    private String author;
    @Schema(description = "작성일", example = "2021-01-01")
    private String createdDate;
    @Schema(description = "수정일", example = "2021-01-01")
    private String modifiedDate;
}
```
>하지만, 현재 프로젝트에서는 모든 Response를 BaseResponseDto에 담아서 반환하고 있기 때문에, example을 작성한다고 해도 적용되지 않는다.


### Controller 작성
Controller 클래스는 API 요청을 처리하는 클래스.
Entry Point 별 Swagger 문서를 작성하고자 한다면, @Operation 어노테이션을 사용하여 정의할 수 있다.
```java
    @Operation(summary = "게시글 등록", description = "게시글을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 등록 성공"),
            @ApiResponse(responseCode = "400", description = "게시글 등록 실패")
    })
    @PostMapping("/example")
    public BaseResponseDto<?> postExample(@RequestBody PostRequestDTO postRequestDTO) {
        return exampleService.postExample(postRequestDTO);
    }
    
```
@ApiResponse 어노테이션을 사용하여 응답 코드와 해당 코드에 대한 설명을 정의할 수 있다.