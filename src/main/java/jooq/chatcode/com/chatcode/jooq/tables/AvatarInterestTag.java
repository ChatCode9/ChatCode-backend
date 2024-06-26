/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables;


import com.chatcode.jooq.DefaultSchema;
import com.chatcode.jooq.Keys;
import com.chatcode.jooq.tables.Avatar.AvatarPath;
import com.chatcode.jooq.tables.InterestTag.InterestTagPath;
import com.chatcode.jooq.tables.records.AvatarInterestTagRecord;

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
public class AvatarInterestTag extends TableImpl<AvatarInterestTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>avatar_interest_tag</code>
     */
    public static final AvatarInterestTag AVATAR_INTEREST_TAG = new AvatarInterestTag();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AvatarInterestTagRecord> getRecordType() {
        return AvatarInterestTagRecord.class;
    }

    /**
     * The column <code>avatar_interest_tag.id</code>.
     */
    public final TableField<AvatarInterestTagRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>avatar_interest_tag.avatar_id</code>.
     */
    public final TableField<AvatarInterestTagRecord, Long> AVATAR_ID = createField(DSL.name("avatar_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>avatar_interest_tag.interest_tag_id</code>.
     */
    public final TableField<AvatarInterestTagRecord, Long> INTEREST_TAG_ID = createField(DSL.name("interest_tag_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private AvatarInterestTag(Name alias, Table<AvatarInterestTagRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private AvatarInterestTag(Name alias, Table<AvatarInterestTagRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>avatar_interest_tag</code> table reference
     */
    public AvatarInterestTag(String alias) {
        this(DSL.name(alias), AVATAR_INTEREST_TAG);
    }

    /**
     * Create an aliased <code>avatar_interest_tag</code> table reference
     */
    public AvatarInterestTag(Name alias) {
        this(alias, AVATAR_INTEREST_TAG);
    }

    /**
     * Create a <code>avatar_interest_tag</code> table reference
     */
    public AvatarInterestTag() {
        this(DSL.name("avatar_interest_tag"), null);
    }

    public <O extends Record> AvatarInterestTag(Table<O> path, ForeignKey<O, AvatarInterestTagRecord> childPath, InverseForeignKey<O, AvatarInterestTagRecord> parentPath) {
        super(path, childPath, parentPath, AVATAR_INTEREST_TAG);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class AvatarInterestTagPath extends AvatarInterestTag implements Path<AvatarInterestTagRecord> {
        public <O extends Record> AvatarInterestTagPath(Table<O> path, ForeignKey<O, AvatarInterestTagRecord> childPath, InverseForeignKey<O, AvatarInterestTagRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private AvatarInterestTagPath(Name alias, Table<AvatarInterestTagRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public AvatarInterestTagPath as(String alias) {
            return new AvatarInterestTagPath(DSL.name(alias), this);
        }

        @Override
        public AvatarInterestTagPath as(Name alias) {
            return new AvatarInterestTagPath(alias, this);
        }

        @Override
        public AvatarInterestTagPath as(Table<?> alias) {
            return new AvatarInterestTagPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<AvatarInterestTagRecord, Long> getIdentity() {
        return (Identity<AvatarInterestTagRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<AvatarInterestTagRecord> getPrimaryKey() {
        return Keys.PK_AVATAR_INTEREST_TAG;
    }

    @Override
    public List<ForeignKey<AvatarInterestTagRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_AVATAR_TO_AVATAR_INTEREST_TAG_1, Keys.FK_TAG_TO_AVATAR_INTEREST_TAG_1);
    }

    private transient AvatarPath _avatar;

    /**
     * Get the implicit join path to the <code>PUBLIC.avatar</code> table.
     */
    public AvatarPath avatar() {
        if (_avatar == null)
            _avatar = new AvatarPath(this, Keys.FK_AVATAR_TO_AVATAR_INTEREST_TAG_1, null);

        return _avatar;
    }

    private transient InterestTagPath _interestTag;

    /**
     * Get the implicit join path to the <code>PUBLIC.interest_tag</code> table.
     */
    public InterestTagPath interestTag() {
        if (_interestTag == null)
            _interestTag = new InterestTagPath(this, Keys.FK_TAG_TO_AVATAR_INTEREST_TAG_1, null);

        return _interestTag;
    }

    @Override
    public AvatarInterestTag as(String alias) {
        return new AvatarInterestTag(DSL.name(alias), this);
    }

    @Override
    public AvatarInterestTag as(Name alias) {
        return new AvatarInterestTag(alias, this);
    }

    @Override
    public AvatarInterestTag as(Table<?> alias) {
        return new AvatarInterestTag(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AvatarInterestTag rename(String name) {
        return new AvatarInterestTag(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AvatarInterestTag rename(Name name) {
        return new AvatarInterestTag(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AvatarInterestTag rename(Table<?> name) {
        return new AvatarInterestTag(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AvatarInterestTag where(Condition condition) {
        return new AvatarInterestTag(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AvatarInterestTag where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AvatarInterestTag where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AvatarInterestTag where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AvatarInterestTag where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AvatarInterestTag where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AvatarInterestTag where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AvatarInterestTag where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AvatarInterestTag whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AvatarInterestTag whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
