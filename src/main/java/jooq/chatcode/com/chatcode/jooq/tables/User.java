/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables;


import com.chatcode.jooq.DefaultSchema;
import com.chatcode.jooq.Keys;
import com.chatcode.jooq.tables.Role.RolePath;
import com.chatcode.jooq.tables.UserRole.UserRolePath;
import com.chatcode.jooq.tables.records.UserRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>user.id</code>.
     */
    public final TableField<UserRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>user.avatar_id</code>.
     */
    public final TableField<UserRecord, Long> AVATAR_ID = createField(DSL.name("avatar_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>user.version</code>.
     */
    public final TableField<UserRecord, Long> VERSION = createField(DSL.name("version"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>user.create_ip</code>.
     */
    public final TableField<UserRecord, String> CREATE_IP = createField(DSL.name("create_ip"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>user.date_created</code>.
     */
    public final TableField<UserRecord, LocalDateTime> DATE_CREATED = createField(DSL.name("date_created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>user.date_withdraw</code>.
     */
    public final TableField<UserRecord, LocalDateTime> DATE_WITHDRAW = createField(DSL.name("date_withdraw"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>user.last_update_ip</code>.
     */
    public final TableField<UserRecord, String> LAST_UPDATE_IP = createField(DSL.name("last_update_ip"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>user.last_updated</code>.
     */
    public final TableField<UserRecord, LocalDateTime> LAST_UPDATED = createField(DSL.name("last_updated"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>user.username</code>.
     */
    public final TableField<UserRecord, String> USERNAME = createField(DSL.name("username"), SQLDataType.VARCHAR(30).nullable(false), this, "");

    /**
     * The column <code>user.withdraw</code>.
     */
    public final TableField<UserRecord, Boolean> WITHDRAW = createField(DSL.name("withdraw"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>user.status</code>.
     */
    public final TableField<UserRecord, Integer> STATUS = createField(DSL.name("status"), SQLDataType.INTEGER.nullable(false), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    public <O extends Record> User(Table<O> path, ForeignKey<O, UserRecord> childPath, InverseForeignKey<O, UserRecord> parentPath) {
        super(path, childPath, parentPath, USER);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UserPath extends User implements Path<UserRecord> {
        public <O extends Record> UserPath(Table<O> path, ForeignKey<O, UserRecord> childPath, InverseForeignKey<O, UserRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UserPath(Name alias, Table<UserRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UserPath as(String alias) {
            return new UserPath(DSL.name(alias), this);
        }

        @Override
        public UserPath as(Name alias) {
            return new UserPath(alias, this);
        }

        @Override
        public UserPath as(Table<?> alias) {
            return new UserPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<UserRecord, Long> getIdentity() {
        return (Identity<UserRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.PK_USER;
    }

    @Override
    public List<UniqueKey<UserRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.CONSTRAINT_36);
    }

    private transient UserRolePath _userRole;

    /**
     * Get the implicit to-many join path to the <code>PUBLIC.user_role</code>
     * table
     */
    public UserRolePath userRole() {
        if (_userRole == null)
            _userRole = new UserRolePath(this, null, Keys.FK_USER_TO_USER_ROLE_1.getInverseKey());

        return _userRole;
    }

    /**
     * Get the implicit many-to-many join path to the <code>PUBLIC.role</code>
     * table
     */
    public RolePath role() {
        return userRole().role();
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    @Override
    public User as(Table<?> alias) {
        return new User(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Table<?> name) {
        return new User(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Condition condition) {
        return new User(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public User where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public User whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
