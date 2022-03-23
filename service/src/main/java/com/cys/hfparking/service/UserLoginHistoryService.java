package com.cys.hfparking.service;

import com.cys.hfparking.vo.ResultVO;

public interface UserLoginHistoryService {
    //添加用户登录日志
    void addLoginHistory(String ip,int uid);
    //查询用户登录日志
    ResultVO findAllLoginHistory(int pageNum);
}
