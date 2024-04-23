/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables;


import com.chatcode.jooq.DefaultSchema;
import com.chatcode.jooq.Keys;
import com.chatcode.jooq.tables.records.FileRecord;

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
public class File extends TableImpl<FileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>file</code>
     */
    public static final File FILE = new File();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FileRecord> getRecordType() {
        return FileRecord.class;
    }

    /**
     * The column <code>file.id</code>.
     */
    public final TableField<FileRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>file.version</code>.
     */
    public final TableField<FileRecord, Long> VERSION = createField(DSL.name("version"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>file.attach_type</code>.
     */
    public final TableField<FileRecord, String> ATTACH_TYPE = createField(DSL.name("attach_type"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>file.byte_size</code>.
     */
    public final TableField<FileRecord, Integer> BYTE_SIZE = createField(DSL.name("byte_size"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>file.height</code>.
     */
    public final TableField<FileRecord, Integer> HEIGHT = createField(DSL.name("height"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>file.width</code>.
     */
    public final TableField<FileRecord, Integer> WIDTH = createField(DSL.name("width"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>file.name</code>.
     */
    public final TableField<FileRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>file.org_name</code>.
     */
    public final TableField<FileRecord, String> ORG_NAME = createField(DSL.name("org_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>file.type</code>.
     */
    public final TableField<FileRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private File(Name alias, Table<FileRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private File(Name alias, Table<FileRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>file</code> table reference
     */
    public File(String alias) {
        this(DSL.name(alias), FILE);
    }

    /**
     * Create an aliased <code>file</code> table reference
     */
    public File(Name alias) {
        this(alias, FILE);
    }

    /**
     * Create a <code>file</code> table reference
     */
    public File() {
        this(DSL.name("file"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<FileRecord> getPrimaryKey() {
        return Keys.PK_FILE;
    }

    @Override
    public File as(String alias) {
        return new File(DSL.name(alias), this);
    }

    @Override
    public File as(Name alias) {
        return new File(alias, this);
    }

    @Override
    public File as(Table<?> alias) {
        return new File(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(String name) {
        return new File(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(Name name) {
        return new File(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(Table<?> name) {
        return new File(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public File where(Condition condition) {
        return new File(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public File where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public File where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public File where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public File where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public File where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public File where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public File where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public File whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public File whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
