package com.cys.hfparking.entity;

import javax.persistence.*;

public class Parking {
    /**
     * 车位id
     */
    @Id
    private Integer pid;

    /**
     * 车位类型1：小型车 2：中型车 3：大型车
     */
    @Column(name = "parking_category")
    private Integer parkingCategory;

    /**
     * 车位区域：A、B、C、D四个区域
     */
    @Column(name = "parking_region")
    private String parkingRegion;

    /**
     * 车位是否空闲1：空闲 2：正在使用
     */
    @Column(name = "parking_stop")
    private Integer parkingStop;

    /**
     * 获取车位id
     *
     * @return pid - 车位id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置车位id
     *
     * @param pid 车位id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取车位类型1：小型车 2：中型车 3：大型车
     *
     * @return parking_category - 车位类型1：小型车 2：中型车 3：大型车
     */
    public Integer getParkingCategory() {
        return parkingCategory;
    }

    /**
     * 设置车位类型1：小型车 2：中型车 3：大型车
     *
     * @param parkingCategory 车位类型1：小型车 2：中型车 3：大型车
     */
    public void setParkingCategory(Integer parkingCategory) {
        this.parkingCategory = parkingCategory;
    }

    /**
     * 获取车位区域：A、B、C、D四个区域
     *
     * @return parking_region - 车位区域：A、B、C、D四个区域
     */
    public String getParkingRegion() {
        return parkingRegion;
    }

    /**
     * 设置车位区域：A、B、C、D四个区域
     *
     * @param parkingRegion 车位区域：A、B、C、D四个区域
     */
    public void setParkingRegion(String parkingRegion) {
        this.parkingRegion = parkingRegion;
    }

    /**
     * 获取车位是否空闲1：空闲 2：正在使用
     *
     * @return parking_stop - 车位是否空闲1：空闲 2：正在使用
     */
    public Integer getParkingStop() {
        return parkingStop;
    }

    /**
     * 设置车位是否空闲1：空闲 2：正在使用
     *
     * @param parkingStop 车位是否空闲1：空闲 2：正在使用
     */
    public void setParkingStop(Integer parkingStop) {
        this.parkingStop = parkingStop;
    }
}