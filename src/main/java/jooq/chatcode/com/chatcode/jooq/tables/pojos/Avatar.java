/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Avatar implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Long version;
    private final Integer activityPoint;
    private final String nickname;
    private final String picture;
    private final String content;

    public Avatar(Avatar value) {
        this.id = value.id;
        this.version = value.version;
        this.activityPoint = value.activityPoint;
        this.nickname = value.nickname;
        this.picture = value.picture;
        this.content = value.content;
    }

    public Avatar(
        Long id,
        Long version,
        Integer activityPoint,
        String nickname,
        String picture,
        String content
    ) {
        this.id = id;
        this.version = version;
        this.activityPoint = activityPoint;
        this.nickname = nickname;
        this.picture = picture;
        this.content = content;
    }

    /**
     * Getter for <code>avatar.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>avatar.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>avatar.activity_point</code>.
     */
    public Integer getActivityPoint() {
        return this.activityPoint;
    }

    /**
     * Getter for <code>avatar.nickname</code>.
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Getter for <code>avatar.picture</code>.
     */
    public String getPicture() {
        return this.picture;
    }

    /**
     * Getter for <code>avatar.content</code>.
     */
    public String getContent() {
        return this.content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Avatar other = (Avatar) obj;
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
        if (this.activityPoint == null) {
            if (other.activityPoint != null)
                return false;
        }
        else if (!this.activityPoint.equals(other.activityPoint))
            return false;
        if (this.nickname == null) {
            if (other.nickname != null)
                return false;
        }
        else if (!this.nickname.equals(other.nickname))
            return false;
        if (this.picture == null) {
            if (other.picture != null)
                return false;
        }
        else if (!this.picture.equals(other.picture))
            return false;
        if (this.content == null) {
            if (other.content != null)
                return false;
        }
        else if (!this.content.equals(other.content))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.activityPoint == null) ? 0 : this.activityPoint.hashCode());
        result = prime * result + ((this.nickname == null) ? 0 : this.nickname.hashCode());
        result = prime * result + ((this.picture == null) ? 0 : this.picture.hashCode());
        result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Avatar (");

        sb.append(id);
        sb.append(", ").append(version);
        sb.append(", ").append(activityPoint);
        sb.append(", ").append(nickname);
        sb.append(", ").append(picture);
        sb.append(", ").append(content);

        sb.append(")");
        return sb.toString();
    }
}
