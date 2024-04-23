/*
 * This file is generated by jOOQ.
 */
package com.chatcode.jooq.tables.records;


import com.chatcode.jooq.tables.ArticleTag;

import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ArticleTagRecord extends TableRecordImpl<ArticleTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>article_tag.article_tags_id</code>.
     */
    public ArticleTagRecord setArticleTagsId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>article_tag.article_tags_id</code>.
     */
    public Long getArticleTagsId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>article_tag.tag_id</code>.
     */
    public ArticleTagRecord setTagId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>article_tag.tag_id</code>.
     */
    public Long getTagId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ArticleTagRecord
     */
    public ArticleTagRecord() {
        super(ArticleTag.ARTICLE_TAG);
    }

    /**
     * Create a detached, initialised ArticleTagRecord
     */
    public ArticleTagRecord(Long articleTagsId, Long tagId) {
        super(ArticleTag.ARTICLE_TAG);

        setArticleTagsId(articleTagsId);
        setTagId(tagId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ArticleTagRecord
     */
    public ArticleTagRecord(com.chatcode.jooq.tables.pojos.ArticleTag value) {
        super(ArticleTag.ARTICLE_TAG);

        if (value != null) {
            setArticleTagsId(value.getArticleTagsId());
            setTagId(value.getTagId());
            resetChangedOnNotNull();
        }
    }
}
