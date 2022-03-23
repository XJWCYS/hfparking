package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarMapper extends GeneralDao<Car> {
    List<Car> findAllCar(int start,int limit);
    List<Car> findByKwCar(@Param("ownerName") String ownerName,
                                 @Param("carNum") String carNum,
                                 @Param("start") int start,
                                 @Param("limit") int limit);
    int delSel(Integer[] cids);
    int addExcelCars(List<Car> cars);
}