package com.cys.hfparking.service;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.vo.ResultVO;

import java.util.List;

public interface CarService {
    //查询全部车辆
    ResultVO findAllCar(int pageNum);
    //根据id删除车辆信息
    ResultVO delCarByOid(int cid);
    //关键词搜索车辆
    ResultVO selectOwnerByKeyword(String ownerName,String carNum,int pageNum);
    //根据id删除多个车辆信息
    ResultVO delCarByCids(Integer[] cids);
    //添加车辆信息
    ResultVO addCar(Car car);
    //修改车辆信息
    ResultVO updateCar(Car car);
    //查询所有的业主信息
    List<Car> queryAllCar();
    //根据excel添加业主信息
    ResultVO addExcelCars(List<Car> cars);
    //根据excel添加业主信息
    ResultVO addExcelCars1(List<Car> cars);
    //查询全部车辆的个数
    ResultVO findAllCountCar();
}
