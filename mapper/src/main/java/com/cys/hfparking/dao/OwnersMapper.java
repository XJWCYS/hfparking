package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OwnersMapper extends GeneralDao<Owners> {
    List<Owners> selectAllOwner(int start,int limit);
    List<Owners> selectByKwOwner(@Param("oname") String oname,
                                   @Param("address") String address,
                                   @Param("start") int start,
                                   @Param("limit") int limit);
    int delSel(Integer[] oids);
    int addExcelOwners(List<Owners> owners);
}