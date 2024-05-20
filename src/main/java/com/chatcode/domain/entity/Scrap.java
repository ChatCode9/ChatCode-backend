package com.chatcode.domain.entity;


import com.chatcode.domain.entity.Scrap.ScrapId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@IdClass(ScrapId.class)
@Table(name = "scrap")
public class Scrap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "avatar_id")
    private Avatar avatar;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "article_id")
    private Article article;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, name = "date_created")
    private LocalDateTime dateCreated;

    @Version
    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long version;

    public class ScrapId implements Serializable {
        private Long avatar;
        private Long article;
    }
}