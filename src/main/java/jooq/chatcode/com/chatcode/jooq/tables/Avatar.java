/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables;


import com.chatcode.jooq.DefaultSchema;
import com.chatcode.jooq.Keys;
import com.chatcode.jooq.tables.Article.ArticlePath;
import com.chatcode.jooq.tables.AvatarInterestTag.AvatarInterestTagPath;
import com.chatcode.jooq.tables.Follow.FollowPath;
import com.chatcode.jooq.tables.InterestTag.InterestTagPath;
import com.chatcode.jooq.tables.Scrap.ScrapPath;
import com.chatcode.jooq.tables.records.AvatarRecord;

import java.util.Collection;

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
public class Avatar extends TableImpl<AvatarRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>avatar</code>
     */
    public static final Avatar AVATAR = new Avatar();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AvatarRecord> getRecordType() {
        return AvatarRecord.class;
    }

    /**
     * The column <code>avatar.id</code>.
     */
    public final TableField<AvatarRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>avatar.version</code>.
     */
    public final TableField<AvatarRecord, Long> VERSION = createField(DSL.name("version"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>avatar.activity_point</code>.
     */
    public final TableField<AvatarRecord, Integer> ACTIVITY_POINT = createField(DSL.name("activity_point"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>avatar.nickname</code>.
     */
    public final TableField<AvatarRecord, String> NICKNAME = createField(DSL.name("nickname"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>avatar.picture</code>.
     */
    public final TableField<AvatarRecord, String> PICTURE = createField(DSL.name("picture"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private Avatar(Name alias, Table<AvatarRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Avatar(Name alias, Table<AvatarRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>avatar</code> table reference
     */
    public Avatar(String alias) {
        this(DSL.name(alias), AVATAR);
    }

    /**
     * Create an aliased <code>avatar</code> table reference
     */
    public Avatar(Name alias) {
        this(alias, AVATAR);
    }

    /**
     * Create a <code>avatar</code> table reference
     */
    public Avatar() {
        this(DSL.name("avatar"), null);
    }

    public <O extends Record> Avatar(Table<O> path, ForeignKey<O, AvatarRecord> childPath, InverseForeignKey<O, AvatarRecord> parentPath) {
        super(path, childPath, parentPath, AVATAR);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class AvatarPath extends Avatar implements Path<AvatarRecord> {
        public <O extends Record> AvatarPath(Table<O> path, ForeignKey<O, AvatarRecord> childPath, InverseForeignKey<O, AvatarRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private AvatarPath(Name alias, Table<AvatarRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public AvatarPath as(String alias) {
            return new AvatarPath(DSL.name(alias), this);
        }

        @Override
        public AvatarPath as(Name alias) {
            return new AvatarPath(alias, this);
        }

        @Override
        public AvatarPath as(Table<?> alias) {
            return new AvatarPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<AvatarRecord, Long> getIdentity() {
        return (Identity<AvatarRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<AvatarRecord> getPrimaryKey() {
        return Keys.PK_AVATAR;
    }

    private transient AvatarInterestTagPath _avatarInterestTag;

    /**
     * Get the implicit to-many join path to the
     * <code>PUBLIC.avatar_interest_tag</code> table
     */
    public AvatarInterestTagPath avatarInterestTag() {
        if (_avatarInterestTag == null)
            _avatarInterestTag = new AvatarInterestTagPath(this, null, Keys.FK_AVATAR_TO_AVATAR_INTEREST_TAG_1.getInverseKey());

        return _avatarInterestTag;
    }

    private transient FollowPath _fkAvatarToFollow_1;

    /**
     * Get the implicit to-many join path to the <code>PUBLIC.follow</code>
     * table, via the <code>FK_avatar_TO_follow_1</code> key
     */
    public FollowPath fkAvatarToFollow_1() {
        if (_fkAvatarToFollow_1 == null)
            _fkAvatarToFollow_1 = new FollowPath(this, null, Keys.FK_AVATAR_TO_FOLLOW_1.getInverseKey());

        return _fkAvatarToFollow_1;
    }

    private transient FollowPath _fkAvatarToFollow_2;

    /**
     * Get the implicit to-many join path to the <code>PUBLIC.follow</code>
     * table, via the <code>FK_avatar_TO_follow_2</code> key
     */
    public FollowPath fkAvatarToFollow_2() {
        if (_fkAvatarToFollow_2 == null)
            _fkAvatarToFollow_2 = new FollowPath(this, null, Keys.FK_AVATAR_TO_FOLLOW_2.getInverseKey());

        return _fkAvatarToFollow_2;
    }

    private transient ScrapPath _scrap;

    /**
     * Get the implicit to-many join path to the <code>PUBLIC.scrap</code> table
     */
    public ScrapPath scrap() {
        if (_scrap == null)
            _scrap = new ScrapPath(this, null, Keys.FK_AVATAR_TO_SCRAP_1.getInverseKey());

        return _scrap;
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>PUBLIC.interest_tag</code> table
     */
    public InterestTagPath interestTag() {
        return avatarInterestTag().interestTag();
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>PUBLIC.article</code> table
     */
    public ArticlePath article() {
        return scrap().article();
    }

    @Override
    public Avatar as(String alias) {
        return new Avatar(DSL.name(alias), this);
    }

    @Override
    public Avatar as(Name alias) {
        return new Avatar(alias, this);
    }

    @Override
    public Avatar as(Table<?> alias) {
        return new Avatar(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Avatar rename(String name) {
        return new Avatar(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Avatar rename(Name name) {
        return new Avatar(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Avatar rename(Table<?> name) {
        return new Avatar(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Avatar where(Condition condition) {
        return new Avatar(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Avatar where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Avatar where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Avatar where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Avatar where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Avatar where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Avatar where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Avatar where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Avatar whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Avatar whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
