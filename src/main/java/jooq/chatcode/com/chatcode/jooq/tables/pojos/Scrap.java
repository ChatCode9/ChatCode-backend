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
public class Scrap implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long avatarId;
    private final Long articleId;
    private final Long version;
    private final LocalDateTime dateCreated;

    public Scrap(Scrap value) {
        this.avatarId = value.avatarId;
        this.articleId = value.articleId;
        this.version = value.version;
        this.dateCreated = value.dateCreated;
    }

    public Scrap(
        Long avatarId,
        Long articleId,
        Long version,
        LocalDateTime dateCreated
    ) {
        this.avatarId = avatarId;
        this.articleId = articleId;
        this.version = version;
        this.dateCreated = dateCreated;
    }

    /**
     * Getter for <code>scrap.avatar_id</code>.
     */
    public Long getAvatarId() {
        return this.avatarId;
    }

    /**
     * Getter for <code>scrap.article_id</code>.
     */
    public Long getArticleId() {
        return this.articleId;
    }

    /**
     * Getter for <code>scrap.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>scrap.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Scrap other = (Scrap) obj;
        if (this.avatarId == null) {
            if (other.avatarId != null)
                return false;
        }
        else if (!this.avatarId.equals(other.avatarId))
            return false;
        if (this.articleId == null) {
            if (other.articleId != null)
                return false;
        }
        else if (!this.articleId.equals(other.articleId))
            return false;
        if (this.version == null) {
            if (other.version != null)
                return false;
        }
        else if (!this.version.equals(other.version))
            return false;
        if (this.dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        }
        else if (!this.dateCreated.equals(other.dateCreated))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.avatarId == null) ? 0 : this.avatarId.hashCode());
        result = prime * result + ((this.articleId == null) ? 0 : this.articleId.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.dateCreated == null) ? 0 : this.dateCreated.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Scrap (");

        sb.append(avatarId);
        sb.append(", ").append(articleId);
        sb.append(", ").append(version);
        sb.append(", ").append(dateCreated);

        sb.append(")");
        return sb.toString();
    }
}
