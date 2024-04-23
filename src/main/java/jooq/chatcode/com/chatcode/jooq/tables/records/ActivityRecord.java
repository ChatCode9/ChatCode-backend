/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.Activity;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityRecord extends UpdatableRecordImpl<ActivityRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>activity.id</code>.
     */
    public ActivityRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>activity.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>activity.avatar_id</code>.
     */
    public ActivityRecord setAvatarId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>activity.avatar_id</code>.
     */
    public Long getAvatarId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>activity.article_id</code>.
     */
    public ActivityRecord setArticleId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>activity.article_id</code>.
     */
    public Long getArticleId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>activity.content_id</code>.
     */
    public ActivityRecord setContentId(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>activity.content_id</code>.
     */
    public Long getContentId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>activity.version</code>.
     */
    public ActivityRecord setVersion(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>activity.version</code>.
     */
    public Long getVersion() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>activity.date_created</code>.
     */
    public ActivityRecord setDateCreated(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>activity.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>activity.last_updated</code>.
     */
    public ActivityRecord setLastUpdated(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>activity.last_updated</code>.
     */
    public LocalDateTime getLastUpdated() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>activity.point</code>.
     */
    public ActivityRecord setPoint(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>activity.point</code>.
     */
    public Integer getPoint() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>activity.point_type</code>.
     */
    public ActivityRecord setPointType(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>activity.point_type</code>.
     */
    public String getPointType() {
        return (String) get(8);
    }

    /**
     * Setter for <code>activity.type</code>.
     */
    public ActivityRecord setType(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>activity.type</code>.
     */
    public String getType() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActivityRecord
     */
    public ActivityRecord() {
        super(Activity.ACTIVITY);
    }

    /**
     * Create a detached, initialised ActivityRecord
     */
    public ActivityRecord(Long id, Long avatarId, Long articleId, Long contentId, Long version, LocalDateTime dateCreated, LocalDateTime lastUpdated, Integer point, String pointType, String type) {
        super(Activity.ACTIVITY);

        setId(id);
        setAvatarId(avatarId);
        setArticleId(articleId);
        setContentId(contentId);
        setVersion(version);
        setDateCreated(dateCreated);
        setLastUpdated(lastUpdated);
        setPoint(point);
        setPointType(pointType);
        setType(type);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ActivityRecord
     */
    public ActivityRecord(com.chatcode.jooq.tables.pojos.Activity value) {
        super(Activity.ACTIVITY);

        if (value != null) {
            setId(value.getId());
            setAvatarId(value.getAvatarId());
            setArticleId(value.getArticleId());
            setContentId(value.getContentId());
            setVersion(value.getVersion());
            setDateCreated(value.getDateCreated());
            setLastUpdated(value.getLastUpdated());
            setPoint(value.getPoint());
            setPointType(value.getPointType());
            setType(value.getType());
            resetChangedOnNotNull();
        }
    }
}
