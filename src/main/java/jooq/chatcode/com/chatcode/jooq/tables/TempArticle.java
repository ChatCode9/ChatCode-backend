/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables;


import com.chatcode.jooq.DefaultSchema;
import com.chatcode.jooq.Keys;
import com.chatcode.jooq.tables.records.TempArticleRecord;

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
public class TempArticle extends TableImpl<TempArticleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>temp_article</code>
     */
    public static final TempArticle TEMP_ARTICLE = new TempArticle();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TempArticleRecord> getRecordType() {
        return TempArticleRecord.class;
    }

    /**
     * The column <code>temp_article.id</code>.
     */
    public final TableField<TempArticleRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>temp_article.avatar_id</code>.
     */
    public final TableField<TempArticleRecord, Long> AVATAR_ID = createField(DSL.name("avatar_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>temp_article.title</code>.
     */
    public final TableField<TempArticleRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>temp_article.content</code>.
     */
    public final TableField<TempArticleRecord, String> CONTENT = createField(DSL.name("content"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>temp_article.date_last_saved</code>.
     */
    public final TableField<TempArticleRecord, LocalDateTime> DATE_LAST_SAVED = createField(DSL.name("date_last_saved"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>temp_article.status</code>.
     */
    public final TableField<TempArticleRecord, Integer> STATUS = createField(DSL.name("status"), SQLDataType.INTEGER, this, "");

    private TempArticle(Name alias, Table<TempArticleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TempArticle(Name alias, Table<TempArticleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>temp_article</code> table reference
     */
    public TempArticle(String alias) {
        this(DSL.name(alias), TEMP_ARTICLE);
    }

    /**
     * Create an aliased <code>temp_article</code> table reference
     */
    public TempArticle(Name alias) {
        this(alias, TEMP_ARTICLE);
    }

    /**
     * Create a <code>temp_article</code> table reference
     */
    public TempArticle() {
        this(DSL.name("temp_article"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<TempArticleRecord> getPrimaryKey() {
        return Keys.PK_TEMP_ARTICLE;
    }

    @Override
    public TempArticle as(String alias) {
        return new TempArticle(DSL.name(alias), this);
    }

    @Override
    public TempArticle as(Name alias) {
        return new TempArticle(alias, this);
    }

    @Override
    public TempArticle as(Table<?> alias) {
        return new TempArticle(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TempArticle rename(String name) {
        return new TempArticle(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TempArticle rename(Name name) {
        return new TempArticle(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TempArticle rename(Table<?> name) {
        return new TempArticle(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TempArticle where(Condition condition) {
        return new TempArticle(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TempArticle where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TempArticle where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TempArticle where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TempArticle where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TempArticle where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TempArticle where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TempArticle where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TempArticle whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TempArticle whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
