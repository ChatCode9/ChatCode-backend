/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.LoggedIn;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LoggedInRecord extends UpdatableRecordImpl<LoggedInRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>logged_in.id</code>.
     */
    public LoggedInRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>logged_in.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>logged_in.user_id</code>.
     */
    public LoggedInRecord setUserId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>logged_in.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>logged_in.version</code>.
     */
    public LoggedInRecord setVersion(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>logged_in.version</code>.
     */
    public Long getVersion() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>logged_in.date_created</code>.
     */
    public LoggedInRecord setDateCreated(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>logged_in.date_created</code>.
     */
    public LocalDateTime getDateCreated() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>logged_in.remote_addr</code>.
     */
    public LoggedInRecord setRemoteAddr(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>logged_in.remote_addr</code>.
     */
    public String getRemoteAddr() {
        return (String) get(4);
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
     * Create a detached LoggedInRecord
     */
    public LoggedInRecord() {
        super(LoggedIn.LOGGED_IN);
    }

    /**
     * Create a detached, initialised LoggedInRecord
     */
    public LoggedInRecord(Long id, Long userId, Long version, LocalDateTime dateCreated, String remoteAddr) {
        super(LoggedIn.LOGGED_IN);

        setId(id);
        setUserId(userId);
        setVersion(version);
        setDateCreated(dateCreated);
        setRemoteAddr(remoteAddr);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised LoggedInRecord
     */
    public LoggedInRecord(com.chatcode.jooq.tables.pojos.LoggedIn value) {
        super(LoggedIn.LOGGED_IN);

        if (value != null) {
            setId(value.getId());
            setUserId(value.getUserId());
            setVersion(value.getVersion());
            setDateCreated(value.getDateCreated());
            setRemoteAddr(value.getRemoteAddr());
            resetChangedOnNotNull();
        }
    }
}
