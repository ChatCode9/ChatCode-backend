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
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final LocalDateTime dateCreated;
    private final String name;
    private final Integer taggedCount;

    public Tag(Tag value) {
        this.id = value.id;
        this.dateCreated = value.dateCreated;
        this.name = value.name;
        this.taggedCount = value.taggedCount;
    }

    public Tag(
        Long id,
        LocalDateTime dateCreated,
        String name,
        Integer taggedCount
    ) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
        this.taggedCount = taggedCount;
    }

    /**
     * Getter for <code>tag.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>tag.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Getter for <code>tag.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>tag.tagged_count</code>.
     */
    public Integer getTaggedCount() {
        return this.taggedCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Tag other = (Tag) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        }
        else if (!this.dateCreated.equals(other.dateCreated))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.taggedCount == null) {
            if (other.taggedCount != null)
                return false;
        }
        else if (!this.taggedCount.equals(other.taggedCount))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.dateCreated == null) ? 0 : this.dateCreated.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.taggedCount == null) ? 0 : this.taggedCount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tag (");

        sb.append(id);
        sb.append(", ").append(dateCreated);
        sb.append(", ").append(name);
        sb.append(", ").append(taggedCount);

        sb.append(")");
        return sb.toString();
    }
}
