package com.chatcode.domain.entity;

import com.chatcode.dto.like.LikeRequest;
import com.chatcode.dto.like.Reactable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "opinion")
public class Opinion implements Reactable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id", nullable = false)
    private Long contentId;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "comment", nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Column(name = "dislike_count", nullable = false)
    private int dislikeCount;

    public void updateLikeCount(LikeRequest likeRequest){
        if(likeRequest.getIsLike()){
            this.likeCount++;
        } else{
            this.dislikeCount++;
        }
    }
}