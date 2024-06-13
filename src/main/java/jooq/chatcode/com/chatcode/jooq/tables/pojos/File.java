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
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String url;
    private final Long targetId;
    private final LocalDateTime dateCreated;
    private final LocalDateTime lastUpdated;

    public File(File value) {
        this.id = value.id;
        this.url = value.url;
        this.targetId = value.targetId;
        this.dateCreated = value.dateCreated;
        this.lastUpdated = value.lastUpdated;
    }

    public File(
        Long id,
        String url,
        Long targetId,
        LocalDateTime dateCreated,
        LocalDateTime lastUpdated
    ) {
        this.id = id;
        this.url = url;
        this.targetId = targetId;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Getter for <code>file.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>file.url</code>.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Getter for <code>file.target_id</code>.
     */
    public Long getTargetId() {
        return this.targetId;
    }

    /**
     * Getter for <code>file.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Getter for <code>file.last_updated</code>.
     */
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final File other = (File) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.url == null) {
            if (other.url != null)
                return false;
        }
        else if (!this.url.equals(other.url))
            return false;
        if (this.targetId == null) {
            if (other.targetId != null)
                return false;
        }
        else if (!this.targetId.equals(other.targetId))
            return false;
        if (this.dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        }
        else if (!this.dateCreated.equals(other.dateCreated))
            return false;
        if (this.lastUpdated == null) {
            if (other.lastUpdated != null)
                return false;
        }
        else if (!this.lastUpdated.equals(other.lastUpdated))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
        result = prime * result + ((this.targetId == null) ? 0 : this.targetId.hashCode());
        result = prime * result + ((this.dateCreated == null) ? 0 : this.dateCreated.hashCode());
        result = prime * result + ((this.lastUpdated == null) ? 0 : this.lastUpdated.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("File (");

        sb.append(id);
        sb.append(", ").append(url);
        sb.append(", ").append(targetId);
        sb.append(", ").append(dateCreated);
        sb.append(", ").append(lastUpdated);

        sb.append(")");
        return sb.toString();
    }
}
