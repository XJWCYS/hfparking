package com.cys.hfparking.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;

public class Owners {
    /**
     * 业主id
     */
    @Id
    @Excel(name = "编号", orderNum = "0")
    private Integer oid;

    /**
     * 业主姓名
     */
    @Excel(name = "姓名", orderNum = "1")
    private String oname;

    /**
     * 业主性别
     */
    @Excel(name = "性别", orderNum = "2")
    private String sex;

    /**
     * 业主电话
     */
    @Excel(name = "电话", orderNum = "3")
    private String tel;

    /**
     * 小区住址
     */
    @Excel(name = "住址", orderNum = "4",width = 25)
    private String address;

    /**
     * 是否户主 1：是 0：否
     */
    @Excel(name = "是否户主(1：是 0：否)", orderNum = "5", width = 20)
    @Column(name = "house_holder")
    private String houseHolder;

    /**
     * 获取业主id
     *
     * @return oid - 业主id
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * 设置业主id
     *
     * @param oid 业主id
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * 获取业主姓名
     *
     * @return oname - 业主姓名
     */
    public String getOname() {
        return oname;
    }

    /**
     * 设置业主姓名
     *
     * @param oname 业主姓名
     */
    public void setOname(String oname) {
        this.oname = oname;
    }

    /**
     * 获取业主性别
     *
     * @return sex - 业主性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置业主性别
     *
     * @param sex 业主性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取业主电话
     *
     * @return tel - 业主电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置业主电话
     *
     * @param tel 业主电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取小区住址
     *
     * @return address - 小区住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置小区住址
     *
     * @param address 小区住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取是否户主 1：是 0：否
     *
     * @return house_holder - 是否户主 1：是 0：否
     */
    public String getHouseHolder() {
        return houseHolder;
    }

    /**
     * 设置是否户主 1：是 0：否
     *
     * @param houseHolder 是否户主 1：是 0：否
     */
    public void setHouseHolder(String houseHolder) {
        this.houseHolder = houseHolder;
    }

    @Override
    public String toString() {
        return "Owners{" +
                "oid=" + oid +
                ", oname='" + oname + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", houseHolder='" + houseHolder + '\'' +
                '}';
    }
}