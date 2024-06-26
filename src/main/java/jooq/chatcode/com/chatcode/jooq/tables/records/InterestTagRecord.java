/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.InterestTag;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InterestTagRecord extends UpdatableRecordImpl<InterestTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>interest_tag.id</code>.
     */
    public InterestTagRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>interest_tag.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>interest_tag.name</code>.
     */
    public InterestTagRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>interest_tag.name</code>.
     */
    public String getName() {
        return (String) get(1);
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
     * Create a detached InterestTagRecord
     */
    public InterestTagRecord() {
        super(InterestTag.INTEREST_TAG);
    }

    /**
     * Create a detached, initialised InterestTagRecord
     */
    public InterestTagRecord(Long id, String name) {
        super(InterestTag.INTEREST_TAG);

        setId(id);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised InterestTagRecord
     */
    public InterestTagRecord(com.chatcode.jooq.tables.pojos.InterestTag value) {
        super(InterestTag.INTEREST_TAG);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            resetChangedOnNotNull();
        }
    }
}
