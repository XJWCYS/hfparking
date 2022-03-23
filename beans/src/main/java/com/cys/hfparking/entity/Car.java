package com.cys.hfparking.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;

public class Car {
    /**
     * 汽车编号
     */
    @Id
    @Excel(name = "汽车编号", orderNum = "0")
    private Integer cid;

    /**
     * 车牌号码
     */
    @Column(name = "car_num")
    @Excel(name = "车牌号", orderNum = "1")
    private String carNum;

    /**
     * 车辆类型1：小型车 2：中型车 3：大型车
     */
    @Column(name = "car_category")
    @Excel(name = "车辆类型(1:小型车 2:中型车 3:大型车)", orderNum = "2",width = 20)
    private Integer carCategory;

    /**
     * 车辆型号
     */
    @Column(name = "car_model")
    @Excel(name = "车辆型号", orderNum = "3")
    private String carModel;

    /**
     * 车辆颜色
     */
    @Column(name = "car_color")
    @Excel(name = "车辆颜色", orderNum = "4")
    private String carColor;

    /**
     * 车辆所属人
     */
    @Column(name = "owner_name")
    @Excel(name = "车主姓名", orderNum = "5")
    private String ownerName;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    @Excel(name = "联系电话", orderNum = "6",width = 25)
    private String phone;

    /**
     * 获取汽车编号
     *
     * @return cid - 汽车编号
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置汽车编号
     *
     * @param cid 汽车编号
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取车牌号码
     *
     * @return car_num - 车牌号码
     */
    public String getCarNum() {
        return carNum;
    }

    /**
     * 设置车牌号码
     *
     * @param carNum 车牌号码
     */
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    /**
     * 获取车辆类型1：小型车 2：中型车 3：大型车
     *
     * @return car_category - 车辆类型1：小型车 2：中型车 3：大型车
     */
    public Integer getCarCategory() {
        return carCategory;
    }

    /**
     * 设置车辆类型1：小型车 2：中型车 3：大型车
     *
     * @param carCategory 车辆类型1：小型车 2：中型车 3：大型车
     */
    public void setCarCategory(Integer carCategory) {
        this.carCategory = carCategory;
    }

    /**
     * 获取车辆型号
     *
     * @return car_model - 车辆型号
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * 设置车辆型号
     *
     * @param carModel 车辆型号
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * 获取车辆颜色
     *
     * @return car_color - 车辆颜色
     */
    public String getCarColor() {
        return carColor;
    }

    /**
     * 设置车辆颜色
     *
     * @param carColor 车辆颜色
     */
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    /**
     * 获取车辆所属人
     *
     * @return owner_name - 车辆所属人
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置车辆所属人
     *
     * @param ownerName 车辆所属人
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}