/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AreaCityCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final Long version;
    private final String name;

    public AreaCityCode(AreaCityCode value) {
        this.id = value.id;
        this.version = value.version;
        this.name = value.name;
    }

    public AreaCityCode(
        String id,
        Long version,
        String name
    ) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    /**
     * Getter for <code>area_city_code.id</code>.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter for <code>area_city_code.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>area_city_code.name</code>.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AreaCityCode other = (AreaCityCode) obj;
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
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AreaCityCode (");

        sb.append(id);
        sb.append(", ").append(version);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
