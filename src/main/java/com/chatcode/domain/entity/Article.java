package com.chatcode.domain.entity;

import com.chatcode.dto.like.LikeRequest;
import com.chatcode.dto.like.Likeable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "article")
public class Article implements Likeable {
//TODO Entity 체크
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long version;

  @Column(name = "author_id")
  private Long authorId;

  @Column
  private Boolean completed;

  @Column(name = "content_id")
  private Long contentId;

  @Column(name = "create_ip")
  private String createIp;

  @Column(name = "date_created", nullable = false)
  private LocalDateTime dateCreated;

  @Column(nullable = false)
  private Boolean enabled;

  @Column(name = "last_editor_id")
  private Long lastEditorId;

  @Column(name = "last_updated", nullable = false)
  private LocalDateTime lastUpdated;

  @Column(name = "note_count", nullable = false)
  private Integer noteCount;

  @Column(name = "scrap_count", nullable = false)
  private Integer scrapCount;

  @Column(name = "selected_note_id")
  private Long selectedNoteId;

  @Column(name = "tag_string")
  private String tagString;

  @Column(nullable = false)
  private String title;

  @Column(name = "view_count", nullable = false)
  private Integer viewCount;

  @Column(name = "like_count", nullable = false)
  private Integer likeCount;

  @Column(name = "dislike_count", nullable = false)
  private Integer dislikeCount;

  @Column(name = "category_id", nullable = false)
  private String categoryId;

  public void updateLikeCount(LikeRequest likeRequest) {
    if (likeRequest.getIsLike()) {
      this.likeCount++;
    } else {
      this.dislikeCount++;
    }
  }
}

