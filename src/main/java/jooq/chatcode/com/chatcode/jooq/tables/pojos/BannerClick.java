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
public class BannerClick implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final Long version;
    private final Long bannerId;
    private final Integer clickCount;
    private final LocalDateTime dateCreated;
    private final String ip;

    public BannerClick(BannerClick value) {
        this.id = value.id;
        this.version = value.version;
        this.bannerId = value.bannerId;
        this.clickCount = value.clickCount;
        this.dateCreated = value.dateCreated;
        this.ip = value.ip;
    }

    public BannerClick(
        Long id,
        Long version,
        Long bannerId,
        Integer clickCount,
        LocalDateTime dateCreated,
        String ip
    ) {
        this.id = id;
        this.version = version;
        this.bannerId = bannerId;
        this.clickCount = clickCount;
        this.dateCreated = dateCreated;
        this.ip = ip;
    }

    /**
     * Getter for <code>banner_click.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>banner_click.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>banner_click.banner_id</code>.
     */
    public Long getBannerId() {
        return this.bannerId;
    }

    /**
     * Getter for <code>banner_click.click_count</code>.
     */
    public Integer getClickCount() {
        return this.clickCount;
    }

    /**
     * Getter for <code>banner_click.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Getter for <code>banner_click.ip</code>.
     */
    public String getIp() {
        return this.ip;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BannerClick other = (BannerClick) obj;
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
        if (this.bannerId == null) {
            if (other.bannerId != null)
                return false;
        }
        else if (!this.bannerId.equals(other.bannerId))
            return false;
        if (this.clickCount == null) {
            if (other.clickCount != null)
                return false;
        }
        else if (!this.clickCount.equals(other.clickCount))
            return false;
        if (this.dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        }
        else if (!this.dateCreated.equals(other.dateCreated))
            return false;
        if (this.ip == null) {
            if (other.ip != null)
                return false;
        }
        else if (!this.ip.equals(other.ip))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.bannerId == null) ? 0 : this.bannerId.hashCode());
        result = prime * result + ((this.clickCount == null) ? 0 : this.clickCount.hashCode());
        result = prime * result + ((this.dateCreated == null) ? 0 : this.dateCreated.hashCode());
        result = prime * result + ((this.ip == null) ? 0 : this.ip.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BannerClick (");

        sb.append(id);
        sb.append(", ").append(version);
        sb.append(", ").append(bannerId);
        sb.append(", ").append(clickCount);
        sb.append(", ").append(dateCreated);
        sb.append(", ").append(ip);

        sb.append(")");
        return sb.toString();
    }
}
