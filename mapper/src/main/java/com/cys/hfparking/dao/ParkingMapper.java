package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParkingMapper extends GeneralDao<Parking> {
    //全部车位
    List<Parking> findAll(@Param("start") int start,
                           @Param("limit") int limit);
    //空闲车位
    List<Parking> findFree(@Param("start") int start,
                           @Param("limit") int limit);
}