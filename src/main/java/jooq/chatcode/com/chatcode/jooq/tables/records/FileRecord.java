/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.File;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FileRecord extends UpdatableRecordImpl<FileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>file.id</code>.
     */
    public FileRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>file.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>file.version</code>.
     */
    public FileRecord setVersion(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>file.version</code>.
     */
    public Long getVersion() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>file.attach_type</code>.
     */
    public FileRecord setAttachType(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>file.attach_type</code>.
     */
    public String getAttachType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>file.byte_size</code>.
     */
    public FileRecord setByteSize(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>file.byte_size</code>.
     */
    public Integer getByteSize() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>file.height</code>.
     */
    public FileRecord setHeight(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>file.height</code>.
     */
    public Integer getHeight() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>file.width</code>.
     */
    public FileRecord setWidth(Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>file.width</code>.
     */
    public Integer getWidth() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>file.name</code>.
     */
    public FileRecord setName(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>file.name</code>.
     */
    public String getName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>file.org_name</code>.
     */
    public FileRecord setOrgName(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>file.org_name</code>.
     */
    public String getOrgName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>file.type</code>.
     */
    public FileRecord setType(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>file.type</code>.
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
     * Create a detached FileRecord
     */
    public FileRecord() {
        super(File.FILE);
    }

    /**
     * Create a detached, initialised FileRecord
     */
    public FileRecord(Long id, Long version, String attachType, Integer byteSize, Integer height, Integer width, String name, String orgName, String type) {
        super(File.FILE);

        setId(id);
        setVersion(version);
        setAttachType(attachType);
        setByteSize(byteSize);
        setHeight(height);
        setWidth(width);
        setName(name);
        setOrgName(orgName);
        setType(type);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FileRecord
     */
    public FileRecord(com.chatcode.jooq.tables.pojos.File value) {
        super(File.FILE);

        if (value != null) {
            setId(value.getId());
            setVersion(value.getVersion());
            setAttachType(value.getAttachType());
            setByteSize(value.getByteSize());
            setHeight(value.getHeight());
            setWidth(value.getWidth());
            setName(value.getName());
            setOrgName(value.getOrgName());
            setType(value.getType());
            resetChangedOnNotNull();
        }
    }
}