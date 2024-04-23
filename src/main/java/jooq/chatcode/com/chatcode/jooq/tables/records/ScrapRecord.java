/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.Scrap;

import java.time.LocalDateTime;

import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ScrapRecord extends UpdatableRecordImpl<ScrapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>scrap.avatar_id</code>.
     */
    public ScrapRecord setAvatarId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>scrap.avatar_id</code>.
     */
    public Long getAvatarId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>scrap.article_id</code>.
     */
    public ScrapRecord setArticleId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>scrap.article_id</code>.
     */
    public Long getArticleId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>scrap.version</code>.
     */
    public ScrapRecord setVersion(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>scrap.version</code>.
     */
    public Long getVersion() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>scrap.date_created</code>.
     */
    public ScrapRecord setDateCreated(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>scrap.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ScrapRecord
     */
    public ScrapRecord() {
        super(Scrap.SCRAP);
    }

    /**
     * Create a detached, initialised ScrapRecord
     */
    public ScrapRecord(Long avatarId, Long articleId, Long version, LocalDateTime dateCreated) {
        super(Scrap.SCRAP);

        setAvatarId(avatarId);
        setArticleId(articleId);
        setVersion(version);
        setDateCreated(dateCreated);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ScrapRecord
     */
    public ScrapRecord(com.chatcode.jooq.tables.pojos.Scrap value) {
        super(Scrap.SCRAP);

        if (value != null) {
            setAvatarId(value.getAvatarId());
            setArticleId(value.getArticleId());
            setVersion(value.getVersion());
            setDateCreated(value.getDateCreated());
            resetChangedOnNotNull();
        }
    }
}