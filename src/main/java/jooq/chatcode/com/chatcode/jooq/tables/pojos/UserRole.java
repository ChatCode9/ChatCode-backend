/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final Long roleId;

    public UserRole(UserRole value) {
        this.userId = value.userId;
        this.roleId = value.roleId;
    }

    public UserRole(
        Long userId,
        Long roleId
    ) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /**
     * Getter for <code>user_role.user_id</code>.
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Getter for <code>user_role.role_id</code>.
     */
    public Long getRoleId() {
        return this.roleId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserRole other = (UserRole) obj;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.roleId == null) {
            if (other.roleId != null)
                return false;
        }
        else if (!this.roleId.equals(other.roleId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserRole (");

        sb.append(userId);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
    }
}