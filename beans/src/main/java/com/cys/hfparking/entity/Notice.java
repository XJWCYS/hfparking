package com.cys.hfparking.entity;

import java.util.Date;
import javax.persistence.*;

public class Notice {
    /**
     * 通知id
     */
    @Id
    private Integer nid;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 公告状态1：上架 0：下架
     */
    private String status;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 获取通知id
     *
     * @return nid - 通知id
     */
    public Integer getNid() {
        return nid;
    }

    /**
     * 设置通知id
     *
     * @param nid 通知id
     */
    public void setNid(Integer nid) {
        this.nid = nid;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取发布时间
     *
     * @return release_time - 发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     *
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取公告状态1：上架 0：下架
     *
     * @return status - 公告状态1：上架 0：下架
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置公告状态1：上架 0：下架
     *
     * @param status 公告状态1：上架 0：下架
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取通知内容
     *
     * @return content - 通知内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置通知内容
     *
     * @param content 通知内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}