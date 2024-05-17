package com.chatcode.domain.entity.scrap;


import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Avatar;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@IdClass(ScrapId.class)
@Table(name = "scrap")
public class Scrap {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "avatar_id")
    private Avatar avatarId;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, name = "article_id")
    private Article articleId;

    @Column(nullable = false, name = "date_created")
    private LocalDateTime dateCreated;

    @Version
    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long version;

}

