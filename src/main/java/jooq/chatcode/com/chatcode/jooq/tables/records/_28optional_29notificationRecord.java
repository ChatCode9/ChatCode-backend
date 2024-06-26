/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables._28optional_29notification;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class _28optional_29notificationRecord extends UpdatableRecordImpl<_28optional_29notificationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>(optional)notification.id</code>.
     */
    public _28optional_29notificationRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>(optional)notification.article_id</code>.
     */
    public _28optional_29notificationRecord setArticleId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.article_id</code>.
     */
    public Long getArticleId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>(optional)notification.sender_id</code>.
     */
    public _28optional_29notificationRecord setSenderId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.sender_id</code>.
     */
    public Long getSenderId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>(optional)notification.receiver_id</code>.
     */
    public _28optional_29notificationRecord setReceiverId(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.receiver_id</code>.
     */
    public Long getReceiverId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>(optional)notification.content_id</code>.
     */
    public _28optional_29notificationRecord setContentId(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.content_id</code>.
     */
    public Long getContentId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>(optional)notification.version</code>.
     */
    public _28optional_29notificationRecord setVersion(Long value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.version</code>.
     */
    public Long getVersion() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>(optional)notification.date_created</code>.
     */
    public _28optional_29notificationRecord setDateCreated(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>(optional)notification.last_updated</code>.
     */
    public _28optional_29notificationRecord setLastUpdated(LocalDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.last_updated</code>.
     */
    public LocalDateTime getLastUpdated() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>(optional)notification.type</code>.
     */
    public _28optional_29notificationRecord setType(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>(optional)notification.type</code>.
     */
    public String getType() {
        return (String) get(8);
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
     * Create a detached _28optional_29notificationRecord
     */
    public _28optional_29notificationRecord() {
        super(_28optional_29notification._28OPTIONAL_29NOTIFICATION);
    }

    /**
     * Create a detached, initialised _28optional_29notificationRecord
     */
    public _28optional_29notificationRecord(Long id, Long articleId, Long senderId, Long receiverId, Long contentId, Long version, LocalDateTime dateCreated, LocalDateTime lastUpdated, String type) {
        super(_28optional_29notification._28OPTIONAL_29NOTIFICATION);

        setId(id);
        setArticleId(articleId);
        setSenderId(senderId);
        setReceiverId(receiverId);
        setContentId(contentId);
        setVersion(version);
        setDateCreated(dateCreated);
        setLastUpdated(lastUpdated);
        setType(type);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised _28optional_29notificationRecord
     */
    public _28optional_29notificationRecord(com.chatcode.jooq.tables.pojos._28optional_29notification value) {
        super(_28optional_29notification._28OPTIONAL_29NOTIFICATION);

        if (value != null) {
            setId(value.getId());
            setArticleId(value.getArticleId());
            setSenderId(value.getSenderId());
            setReceiverId(value.getReceiverId());
            setContentId(value.getContentId());
            setVersion(value.getVersion());
            setDateCreated(value.getDateCreated());
            setLastUpdated(value.getLastUpdated());
            setType(value.getType());
            resetChangedOnNotNull();
        }
    }
}
