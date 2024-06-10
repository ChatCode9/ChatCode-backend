package com.chatcode.dto.tag;

import com.chatcode.domain.entity.InterestTag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Schema(description = "관심 태그 응답 객체")
@Data
public class InterestTagResponse {

    @Schema(description = "태그 ID", example = "1")
    private Long id;

    @Schema(description = "태그 이름", example = "Java")
    private String name;

    public static InterestTagResponse of(Long id, String name) {
        InterestTagResponse response = new InterestTagResponse();
        response.id = id;
        response.name = name;
        return response;
    }

    public static List<InterestTagResponse> of(List<InterestTag> tags) {
        return tags.stream()
                .map(tag -> InterestTagResponse.of(tag.getId(), tag.getName()))
                .collect(Collectors.toList());
    }
}
