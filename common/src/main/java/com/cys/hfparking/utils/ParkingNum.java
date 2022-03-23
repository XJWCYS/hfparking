package com.cys.hfparking.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingNum {
    //总车位数
    private int count;
    //空闲车位数
    private int free;
    //正在使用车位数
    private int busy;
    //百分比
    private int percentage;

    //小型车位数量
    private int aCount;
    //空闲车位数
    private int aFree;
    //百分比
    private int aPercentage;

    //中型车位数量
    private int bCount;
    //空闲车位数
    private int bFree;
    //百分比
    private int bPercentage;

    //大型车位数量
    private int cCount;
    //空闲车位数
    private int cFree;
    //百分比
    private int cPercentage;




}
