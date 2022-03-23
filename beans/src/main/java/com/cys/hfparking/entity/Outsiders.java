package com.cys.hfparking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

public class Outsiders {
    @Id
    private Integer outid;

    /**
     * 外来人员姓名
     */
    @Column(name = "out_name")
    private String outName;

    /**
     * 外来人员电话
     */
    @Column(name = "out_tel")
    private String outTel;

    /**
     * 外来车辆车牌号
     */
    @Column(name = "out_car")
    private String outCar;

    /**
     * 来访目的
     */
    @Column(name = "visit_purpose")
    private String visitPurpose;

    /**
     * 身份证号
     */
    @Column(name = "id_num")
    private String idNum;

    /**
     * 责任人
     */
    @Column(name = "liable_person")
    private String liablePerson;

    /**
     * 补充说明
     */
    private String remarks;

    /**
     * 进入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")

    @Column(name = "entry_time")
    private Date entryTime;

    /**
     * 离开时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "departure_time")
    private Date departureTime;

    /**
     * @return outid
     */
    public Integer getOutid() {
        return outid;
    }

    /**
     * @param outid
     */
    public void setOutid(Integer outid) {
        this.outid = outid;
    }

    /**
     * 获取外来人员姓名
     *
     * @return out_name - 外来人员姓名
     */
    public String getOutName() {
        return outName;
    }

    /**
     * 设置外来人员姓名
     *
     * @param outName 外来人员姓名
     */
    public void setOutName(String outName) {
        this.outName = outName;
    }

    /**
     * 获取外来人员电话
     *
     * @return out_tel - 外来人员电话
     */
    public String getOutTel() {
        return outTel;
    }

    /**
     * 设置外来人员电话
     *
     * @param outTel 外来人员电话
     */
    public void setOutTel(String outTel) {
        this.outTel = outTel;
    }

    /**
     * 获取外来车辆车牌号
     *
     * @return out_car - 外来车辆车牌号
     */
    public String getOutCar() {
        return outCar;
    }

    /**
     * 设置外来车辆车牌号
     *
     * @param outCar 外来车辆车牌号
     */
    public void setOutCar(String outCar) {
        this.outCar = outCar;
    }

    /**
     * 获取来访目的
     *
     * @return visit_purpose - 来访目的
     */
    public String getVisitPurpose() {
        return visitPurpose;
    }

    /**
     * 设置来访目的
     *
     * @param visitPurpose 来访目的
     */
    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    /**
     * 获取身份证号
     *
     * @return id_num - 身份证号
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * 设置身份证号
     *
     * @param idNum 身份证号
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /**
     * 获取责任人
     *
     * @return liable_person - 责任人
     */
    public String getLiablePerson() {
        return liablePerson;
    }

    /**
     * 设置责任人
     *
     * @param liablePerson 责任人
     */
    public void setLiablePerson(String liablePerson) {
        this.liablePerson = liablePerson;
    }

    /**
     * 获取补充说明
     *
     * @return remarks - 补充说明
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置补充说明
     *
     * @param remarks 补充说明
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取进入时间
     *
     * @return entry_time - 进入时间
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * 设置进入时间
     *
     * @param entryTime 进入时间
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * 获取离开时间
     *
     * @return departure_time - 离开时间
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * 设置离开时间
     *
     * @param departureTime 离开时间
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}