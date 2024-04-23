/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ContentVote implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Long articleId;
    private final Long voterId;
    private final Long contentId;
    private final LocalDateTime dateCreated;
    private final Integer point;

    public ContentVote(ContentVote value) {
        this.id = value.id;
        this.articleId = value.articleId;
        this.voterId = value.voterId;
        this.contentId = value.contentId;
        this.dateCreated = value.dateCreated;
        this.point = value.point;
    }

    public ContentVote(
        Long id,
        Long articleId,
        Long voterId,
        Long contentId,
        LocalDateTime dateCreated,
        Integer point
    ) {
        this.id = id;
        this.articleId = articleId;
        this.voterId = voterId;
        this.contentId = contentId;
        this.dateCreated = dateCreated;
        this.point = point;
    }

    /**
     * Getter for <code>content_vote.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>content_vote.article_id</code>.
     */
    public Long getArticleId() {
        return this.articleId;
    }

    /**
     * Getter for <code>content_vote.voter_id</code>.
     */
    public Long getVoterId() {
        return this.voterId;
    }

    /**
     * Getter for <code>content_vote.content_id</code>.
     */
    public Long getContentId() {
        return this.contentId;
    }

    /**
     * Getter for <code>content_vote.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Getter for <code>content_vote.point</code>.
     */
    public Integer getPoint() {
        return this.point;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ContentVote other = (ContentVote) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.articleId == null) {
            if (other.articleId != null)
                return false;
        }
        else if (!this.articleId.equals(other.articleId))
            return false;
        if (this.voterId == null) {
            if (other.voterId != null)
                return false;
        }
        else if (!this.voterId.equals(other.voterId))
            return false;
        if (this.contentId == null) {
            if (other.contentId != null)
                return false;
        }
        else if (!this.contentId.equals(other.contentId))
            return false;
        if (this.dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        }
        else if (!this.dateCreated.equals(other.dateCreated))
            return false;
        if (this.point == null) {
            if (other.point != null)
                return false;
        }
        else if (!this.point.equals(other.point))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.articleId == null) ? 0 : this.articleId.hashCode());
        result = prime * result + ((this.voterId == null) ? 0 : this.voterId.hashCode());
        result = prime * result + ((this.contentId == null) ? 0 : this.contentId.hashCode());
        result = prime * result + ((this.dateCreated == null) ? 0 : this.dateCreated.hashCode());
        result = prime * result + ((this.point == null) ? 0 : this.point.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ContentVote (");

        sb.append(id);
        sb.append(", ").append(articleId);
        sb.append(", ").append(voterId);
        sb.append(", ").append(contentId);
        sb.append(", ").append(dateCreated);
        sb.append(", ").append(point);

        sb.append(")");
        return sb.toString();
    }
}
