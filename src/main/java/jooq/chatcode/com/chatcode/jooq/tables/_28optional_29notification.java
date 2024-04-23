/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables;


import com.chatcode.jooq.DefaultSchema;
import com.chatcode.jooq.Keys;
import com.chatcode.jooq.tables.records._28optional_29notificationRecord;

import java.time.LocalDateTime;
import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
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
public class _28optional_29notification extends TableImpl<_28optional_29notificationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>(optional)notification</code>
     */
    public static final _28optional_29notification _28OPTIONAL_29NOTIFICATION = new _28optional_29notification();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<_28optional_29notificationRecord> getRecordType() {
        return _28optional_29notificationRecord.class;
    }

    /**
     * The column <code>(optional)notification.id</code>.
     */
    public final TableField<_28optional_29notificationRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>(optional)notification.article_id</code>.
     */
    public final TableField<_28optional_29notificationRecord, Long> ARTICLE_ID = createField(DSL.name("article_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>(optional)notification.sender_id</code>.
     */
    public final TableField<_28optional_29notificationRecord, Long> SENDER_ID = createField(DSL.name("sender_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>(optional)notification.receiver_id</code>.
     */
    public final TableField<_28optional_29notificationRecord, Long> RECEIVER_ID = createField(DSL.name("receiver_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>(optional)notification.content_id</code>.
     */
    public final TableField<_28optional_29notificationRecord, Long> CONTENT_ID = createField(DSL.name("content_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>(optional)notification.version</code>.
     */
    public final TableField<_28optional_29notificationRecord, Long> VERSION = createField(DSL.name("version"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>(optional)notification.date_created</code>.
     */
    public final TableField<_28optional_29notificationRecord, LocalDateTime> DATE_CREATED = createField(DSL.name("date_created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>(optional)notification.last_updated</code>.
     */
    public final TableField<_28optional_29notificationRecord, LocalDateTime> LAST_UPDATED = createField(DSL.name("last_updated"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>(optional)notification.type</code>.
     */
    public final TableField<_28optional_29notificationRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private _28optional_29notification(Name alias, Table<_28optional_29notificationRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private _28optional_29notification(Name alias, Table<_28optional_29notificationRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>(optional)notification</code> table reference
     */
    public _28optional_29notification(String alias) {
        this(DSL.name(alias), _28OPTIONAL_29NOTIFICATION);
    }

    /**
     * Create an aliased <code>(optional)notification</code> table reference
     */
    public _28optional_29notification(Name alias) {
        this(alias, _28OPTIONAL_29NOTIFICATION);
    }

    /**
     * Create a <code>(optional)notification</code> table reference
     */
    public _28optional_29notification() {
        this(DSL.name("(optional)notification"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<_28optional_29notificationRecord> getPrimaryKey() {
        return Keys.PK__28OPTIONAL_29NOTIFICATION;
    }

    @Override
    public _28optional_29notification as(String alias) {
        return new _28optional_29notification(DSL.name(alias), this);
    }

    @Override
    public _28optional_29notification as(Name alias) {
        return new _28optional_29notification(alias, this);
    }

    @Override
    public _28optional_29notification as(Table<?> alias) {
        return new _28optional_29notification(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public _28optional_29notification rename(String name) {
        return new _28optional_29notification(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public _28optional_29notification rename(Name name) {
        return new _28optional_29notification(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public _28optional_29notification rename(Table<?> name) {
        return new _28optional_29notification(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _28optional_29notification where(Condition condition) {
        return new _28optional_29notification(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _28optional_29notification where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _28optional_29notification where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _28optional_29notification where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _28optional_29notification where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _28optional_29notification where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _28optional_29notification where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _28optional_29notification where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _28optional_29notification whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _28optional_29notification whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
