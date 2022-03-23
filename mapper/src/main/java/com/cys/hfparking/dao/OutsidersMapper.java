package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Outsiders;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutsidersMapper extends GeneralDao<Outsiders> {
    List<Outsiders> findAllOutsiders(@Param("outName") String outName,
                                     @Param("outCar") String outCar,
                                     @Param("start") int start,
                                     @Param("limit") int limit);
}