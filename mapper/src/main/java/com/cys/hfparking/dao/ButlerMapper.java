package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Butler;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ButlerMapper extends GeneralDao<Butler> {

    List<Butler> selectByKwButler(@Param("butlerName") String butlerName,
                                 @Param("start") int start,
                                 @Param("limit") int limit);
    List<Butler> findFree(@Param("start") int start,
                          @Param("limit") int limit);
    int delSel(Integer[] bids);
    int addExcelButler(List<Butler> butlers);
}