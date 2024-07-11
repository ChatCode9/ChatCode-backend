package com.chatcode.handler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    INVALID_REQUEST(1000, "올바르지 않은 요청입니다."),

    NOT_FOUND_ARTICLE_ID(1001, "요청한 ID에 해당하는 게시글이 존재하지 않습니다."),
    NOT_FOUND_CONTENT_ID(1002, "요청한 ID에 해당하는 컨텐츠가 존재하지 않습니다."),
    NOT_FOUND_AVATAR_ID(1003, "요청한 ID에 해당하는 아바타가 존재하지 않습니다."),
    NOT_FOUND_CATEGORY_ID(1004, "요청한 ID에 해당하는 카테고리가 존재하지 않습니다."),
    NOT_FOUND_SCRAP_ID(1005, "요청한 ID에 해당하는 스크랩이 존재하지 않습니다."),
    NOT_FOUND_TAG_ID(1006, "요청한 ID에 해당하는 태그가 존재하지 않습니다."),
    NOT_FOUND_RESOURCE_ID(1007, "요청한 ID에 해당하는 리소스가 존재하지 않습니다."),
    NOT_FOUND_CONTENT_FROM_ARTICLE_ID(1008, "요청한 아티클에 해당하는 컨탠츠가이 존재하지 않습니다."),

    INVALID_CATEGORY_ORDER_SIZE(2001, "요청한 카테고리에 누락된 ID가 존재합니다."),
    INVALID_CATEGORY_ORDER_DUPLICATE(2002, "요청한 카테고리에 중복된 ID가 존재합니다."),

    INVALID_IMAGE_URL(3001, "요청한 이미지 URL의 형식이 잘못되었습니다."),
    FAIL_IMAGE_UPLOAD(3002, "이미지 파일 업로드 중 에러가 발생했습니다."),
    EMPTY_IMAGE_FILE(3003, "이미지 파일이 비어있습니다."),

    ALREADY_REACTED(4001, "해당 컨텐츠에 대해서 이미 반응을 하였습니다."),

    INTERNAL_SEVER_ERROR(9999, "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요.");

    private final int code;
    private final String message;
}
