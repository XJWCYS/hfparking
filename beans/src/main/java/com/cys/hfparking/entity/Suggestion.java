package com.cys.hfparking.entity;

import java.util.Date;
import javax.persistence.*;

public class Suggestion {
    /**
     * 建议id
     */
    @Id
    private Integer sid;

    /**
     * 建议内容
     */
    @Column(name = "suggest_content")
    private String suggestContent;

    /**
     * 建议时间
     */
    @Column(name = "suggest_time")
    private Date suggestTime;

    /**
     * 是否回复(1已回复 0:未回复)
     */
    private String status;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 等级
     */
    @Column(name = "suggestion_level")
    private String suggestionLevel;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 是否匿名（1:是，0:否）
     */
    @Column(name = "is_anonymous")
    private Integer isAnonymous;

    /**
     * 获取建议id
     *
     * @return sid - 建议id
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置建议id
     *
     * @param sid 建议id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取建议内容
     *
     * @return suggest_content - 建议内容
     */
    public String getSuggestContent() {
        return suggestContent;
    }

    /**
     * 设置建议内容
     *
     * @param suggestContent 建议内容
     */
    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent;
    }

    /**
     * 获取建议时间
     *
     * @return suggest_time - 建议时间
     */
    public Date getSuggestTime() {
        return suggestTime;
    }

    /**
     * 设置建议时间
     *
     * @param suggestTime 建议时间
     */
    public void setSuggestTime(Date suggestTime) {
        this.suggestTime = suggestTime;
    }

    /**
     * 获取是否回复(1已回复 0:未回复)
     *
     * @return status - 是否回复(1已回复 0:未回复)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置是否回复(1已回复 0:未回复)
     *
     * @param status 是否回复(1已回复 0:未回复)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取父id
     *
     * @return parent_id - 父id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId 父id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取等级
     *
     * @return suggestion_level - 等级
     */
    public String getSuggestionLevel() {
        return suggestionLevel;
    }

    /**
     * 设置等级
     *
     * @param suggestionLevel 等级
     */
    public void setSuggestionLevel(String suggestionLevel) {
        this.suggestionLevel = suggestionLevel;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取是否匿名（1:是，0:否）
     *
     * @return is_anonymous - 是否匿名（1:是，0:否）
     */
    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    /**
     * 设置是否匿名（1:是，0:否）
     *
     * @param isAnonymous 是否匿名（1:是，0:否）
     */
    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
}