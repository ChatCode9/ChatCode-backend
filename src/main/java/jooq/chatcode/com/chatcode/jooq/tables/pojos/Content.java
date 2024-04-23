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
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Long version;
    private final Long articleId;
    private final Long authorId;
    private final String createIp;
    private final LocalDateTime dateCreated;
    private final Long lastEditorId;
    private final LocalDateTime lastUpdated;
    private final String text;
    private final Integer type;
    private final Integer likeCount;
    private final Integer dislikeCount;

    public Content(Content value) {
        this.id = value.id;
        this.version = value.version;
        this.articleId = value.articleId;
        this.authorId = value.authorId;
        this.createIp = value.createIp;
        this.dateCreated = value.dateCreated;
        this.lastEditorId = value.lastEditorId;
        this.lastUpdated = value.lastUpdated;
        this.text = value.text;
        this.type = value.type;
        this.likeCount = value.likeCount;
        this.dislikeCount = value.dislikeCount;
    }

    public Content(
        Long id,
        Long version,
        Long articleId,
        Long authorId,
        String createIp,
        LocalDateTime dateCreated,
        Long lastEditorId,
        LocalDateTime lastUpdated,
        String text,
        Integer type,
        Integer likeCount,
        Integer dislikeCount
    ) {
        this.id = id;
        this.version = version;
        this.articleId = articleId;
        this.authorId = authorId;
        this.createIp = createIp;
        this.dateCreated = dateCreated;
        this.lastEditorId = lastEditorId;
        this.lastUpdated = lastUpdated;
        this.text = text;
        this.type = type;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    /**
     * Getter for <code>content.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>content.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>content.article_id</code>.
     */
    public Long getArticleId() {
        return this.articleId;
    }

    /**
     * Getter for <code>content.author_id</code>.
     */
    public Long getAuthorId() {
        return this.authorId;
    }

    /**
     * Getter for <code>content.create_ip</code>.
     */
    public String getCreateIp() {
        return this.createIp;
    }

    /**
     * Getter for <code>content.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Getter for <code>content.last_editor_id</code>.
     */
    public Long getLastEditorId() {
        return this.lastEditorId;
    }

    /**
     * Getter for <code>content.last_updated</code>.
     */
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    /**
     * Getter for <code>content.text</code>.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Getter for <code>content.type</code>.
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Getter for <code>content.like_count</code>.
     */
    public Integer getLikeCount() {
        return this.likeCount;
    }

    /**
     * Getter for <code>content.dislike_count</code>.
     */
    public Integer getDislikeCount() {
        return this.dislikeCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Content other = (Content) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.version == null) {
            if (other.version != null)
                return false;
        }
        else if (!this.version.equals(other.version))
            return false;
        if (this.articleId == null) {
            if (other.articleId != null)
                return false;
        }
        else if (!this.articleId.equals(other.articleId))
            return false;
        if (this.authorId == null) {
            if (other.authorId != null)
                return false;
        }
        else if (!this.authorId.equals(other.authorId))
            return false;
        if (this.createIp == null) {
            if (other.createIp != null)
                return false;
        }
        else if (!this.createIp.equals(other.createIp))
            return false;
        if (this.dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        }
        else if (!this.dateCreated.equals(other.dateCreated))
            return false;
        if (this.lastEditorId == null) {
            if (other.lastEditorId != null)
                return false;
        }
        else if (!this.lastEditorId.equals(other.lastEditorId))
            return false;
        if (this.lastUpdated == null) {
            if (other.lastUpdated != null)
                return false;
        }
        else if (!this.lastUpdated.equals(other.lastUpdated))
            return false;
        if (this.text == null) {
            if (other.text != null)
                return false;
        }
        else if (!this.text.equals(other.text))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        }
        else if (!this.type.equals(other.type))
            return false;
        if (this.likeCount == null) {
            if (other.likeCount != null)
                return false;
        }
        else if (!this.likeCount.equals(other.likeCount))
            return false;
        if (this.dislikeCount == null) {
            if (other.dislikeCount != null)
                return false;
        }
        else if (!this.dislikeCount.equals(other.dislikeCount))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.articleId == null) ? 0 : this.articleId.hashCode());
        result = prime * result + ((this.authorId == null) ? 0 : this.authorId.hashCode());
        result = prime * result + ((this.createIp == null) ? 0 : this.createIp.hashCode());
        result = prime * result + ((this.dateCreated == null) ? 0 : this.dateCreated.hashCode());
        result = prime * result + ((this.lastEditorId == null) ? 0 : this.lastEditorId.hashCode());
        result = prime * result + ((this.lastUpdated == null) ? 0 : this.lastUpdated.hashCode());
        result = prime * result + ((this.text == null) ? 0 : this.text.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.likeCount == null) ? 0 : this.likeCount.hashCode());
        result = prime * result + ((this.dislikeCount == null) ? 0 : this.dislikeCount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Content (");

        sb.append(id);
        sb.append(", ").append(version);
        sb.append(", ").append(articleId);
        sb.append(", ").append(authorId);
        sb.append(", ").append(createIp);
        sb.append(", ").append(dateCreated);
        sb.append(", ").append(lastEditorId);
        sb.append(", ").append(lastUpdated);
        sb.append(", ").append(text);
        sb.append(", ").append(type);
        sb.append(", ").append(likeCount);
        sb.append(", ").append(dislikeCount);

        sb.append(")");
        return sb.toString();
    }
}