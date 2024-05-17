package com.chatcode.domain.entity.scrap;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapId implements Serializable {
    private Long avatarId;
    private Long articleId;
}
