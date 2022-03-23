package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Butler;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.User;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends GeneralDao<User> {
    List<User> findUserByKw(@Param("username") String username,
                                  @Param("start") int start,
                                  @Param("limit") int limit);
    int delSel(Integer[] ids);
    int addExcelUsers(List<User> users);
    List<User> findUserById(int id);
}