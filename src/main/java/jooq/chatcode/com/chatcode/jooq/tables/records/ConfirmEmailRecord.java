/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.ConfirmEmail;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ConfirmEmailRecord extends UpdatableRecordImpl<ConfirmEmailRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>confirm_email.id</code>.
     */
    public ConfirmEmailRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>confirm_email.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>confirm_email.user_id</code>.
     */
    public ConfirmEmailRecord setUserId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>confirm_email.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>confirm_email.version</code>.
     */
    public ConfirmEmailRecord setVersion(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>confirm_email.version</code>.
     */
    public Long getVersion() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>confirm_email.date_expired</code>.
     */
    public ConfirmEmailRecord setDateExpired(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>confirm_email.date_expired</code>.
     */
    public LocalDateTime getDateExpired() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>confirm_email.email</code>.
     */
    public ConfirmEmailRecord setEmail(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>confirm_email.email</code>.
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>confirm_email.secured_key</code>.
     */
    public ConfirmEmailRecord setSecuredKey(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>confirm_email.secured_key</code>.
     */
    public String getSecuredKey() {
        return (String) get(5);
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
     * Create a detached ConfirmEmailRecord
     */
    public ConfirmEmailRecord() {
        super(ConfirmEmail.CONFIRM_EMAIL);
    }

    /**
     * Create a detached, initialised ConfirmEmailRecord
     */
    public ConfirmEmailRecord(Long id, Long userId, Long version, LocalDateTime dateExpired, String email, String securedKey) {
        super(ConfirmEmail.CONFIRM_EMAIL);

        setId(id);
        setUserId(userId);
        setVersion(version);
        setDateExpired(dateExpired);
        setEmail(email);
        setSecuredKey(securedKey);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ConfirmEmailRecord
     */
    public ConfirmEmailRecord(com.chatcode.jooq.tables.pojos.ConfirmEmail value) {
        super(ConfirmEmail.CONFIRM_EMAIL);

        if (value != null) {
            setId(value.getId());
            setUserId(value.getUserId());
            setVersion(value.getVersion());
            setDateExpired(value.getDateExpired());
            setEmail(value.getEmail());
            setSecuredKey(value.getSecuredKey());
            resetChangedOnNotNull();
        }
    }
}
