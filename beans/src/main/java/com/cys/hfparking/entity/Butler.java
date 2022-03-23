package com.cys.hfparking.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;

public class Butler {
    /**
     * 管家id
     */
    @Id
    @Excel(name = "管家id", orderNum = "0")
    private Integer bid;

    /**
     * 车管家姓名
     */
    @Column(name = "butler_name")
    @Excel(name = "车管家姓名", orderNum = "1")
    private String butlerName;

    /**
     * 车管家电话
     */
    @Column(name = "butler_phone")
    @Excel(name = "车管家电话", orderNum = "2",width = 25)
    private String butlerPhone;

    /**
     * 车管家性别
     */
    @Column(name = "butler_sex")
    @Excel(name = "车管家性别", orderNum = "3")
    private String butlerSex;

    /**
     * 管家状态1：正在值班 2：休息中
     */
    @Excel(name = "管家状态1:正在值班 2:休息中", orderNum = "4",width = 25)
    private String status;

    /**
     * 获取管家id
     *
     * @return bid - 管家id
     */
    public Integer getBid() {
        return bid;
    }

    /**
     * 设置管家id
     *
     * @param bid 管家id
     */
    public void setBid(Integer bid) {
        this.bid = bid;
    }

    /**
     * 获取车管家姓名
     *
     * @return butler_name - 车管家姓名
     */
    public String getButlerName() {
        return butlerName;
    }

    /**
     * 设置车管家姓名
     *
     * @param butlerName 车管家姓名
     */
    public void setButlerName(String butlerName) {
        this.butlerName = butlerName;
    }

    /**
     * 获取车管家电话
     *
     * @return butler_phone - 车管家电话
     */
    public String getButlerPhone() {
        return butlerPhone;
    }

    /**
     * 设置车管家电话
     *
     * @param butlerPhone 车管家电话
     */
    public void setButlerPhone(String butlerPhone) {
        this.butlerPhone = butlerPhone;
    }

    /**
     * 获取车管家性别
     *
     * @return butler_sex - 车管家性别
     */
    public String getButlerSex() {
        return butlerSex;
    }

    /**
     * 设置车管家性别
     *
     * @param butlerSex 车管家性别
     */
    public void setButlerSex(String butlerSex) {
        this.butlerSex = butlerSex;
    }

    /**
     * 获取管家状态1：正在值班 2：休息中
     *
     * @return status - 管家状态1：正在值班 2：休息中
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置管家状态1：正在值班 2：休息中
     *
     * @param status 管家状态1：正在值班 2：休息中
     */
    public void setStatus(String status) {
        this.status = status;
    }
}