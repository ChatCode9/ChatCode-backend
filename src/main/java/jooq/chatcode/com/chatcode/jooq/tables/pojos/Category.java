/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String name;
    private final Integer sortOrder;
    private final Integer type;
    private final Integer parentId;

    public Category(Category value) {
        this.code = value.code;
        this.name = value.name;
        this.sortOrder = value.sortOrder;
        this.type = value.type;
        this.parentId = value.parentId;
    }

    public Category(
        String code,
        String name,
        Integer sortOrder,
        Integer type,
        Integer parentId
    ) {
        this.code = code;
        this.name = name;
        this.sortOrder = sortOrder;
        this.type = type;
        this.parentId = parentId;
    }

    /**
     * Getter for <code>category.code</code>.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Getter for <code>category.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>category.sort_order</code>.
     */
    public Integer getSortOrder() {
        return this.sortOrder;
    }

    /**
     * Getter for <code>category.type</code>.
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Getter for <code>category.parent_id</code>.
     */
    public Integer getParentId() {
        return this.parentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Category other = (Category) obj;
        if (this.code == null) {
            if (other.code != null)
                return false;
        }
        else if (!this.code.equals(other.code))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.sortOrder == null) {
            if (other.sortOrder != null)
                return false;
        }
        else if (!this.sortOrder.equals(other.sortOrder))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        }
        else if (!this.type.equals(other.type))
            return false;
        if (this.parentId == null) {
            if (other.parentId != null)
                return false;
        }
        else if (!this.parentId.equals(other.parentId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.sortOrder == null) ? 0 : this.sortOrder.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.parentId == null) ? 0 : this.parentId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Category (");

        sb.append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(sortOrder);
        sb.append(", ").append(type);
        sb.append(", ").append(parentId);

        sb.append(")");
        return sb.toString();
    }
}
