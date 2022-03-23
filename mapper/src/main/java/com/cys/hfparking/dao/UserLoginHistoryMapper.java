package com.cys.hfparking.dao;

import com.cys.hfparking.entity.UserLoginHistory;
import com.cys.hfparking.entity.UserLoginHistoryVO;
import com.cys.hfparking.general.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserLoginHistoryMapper extends GeneralDao<UserLoginHistory> {
    List<UserLoginHistoryVO> findAllUserLoginHistory(int start,int limit);
}