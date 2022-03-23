package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.UserLoginHistoryMapper;
import com.cys.hfparking.entity.UserLoginHistory;
import com.cys.hfparking.entity.UserLoginHistoryVO;
import com.cys.hfparking.service.UserLoginHistoryService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserLoginHistoryServiceImpl implements UserLoginHistoryService {
    @Autowired
    private UserLoginHistoryMapper userLoginHistoryMapper;
    private int limit = 7;
    @Override
    public void addLoginHistory(String ip, int uid) {
        UserLoginHistory userLoginHistory = new UserLoginHistory();
        userLoginHistory.setIp(ip);
        userLoginHistory.setUid(uid);
        userLoginHistory.setLoginTime(new Date());
        userLoginHistoryMapper.insert(userLoginHistory);
    }

    @Override
    public ResultVO findAllLoginHistory(int pageNum) {
        int start = (pageNum-1)*limit;
        List<UserLoginHistoryVO> allUserLoginHistory = userLoginHistoryMapper.findAllUserLoginHistory(start, limit);
        int count = userLoginHistoryMapper.selectAll().size();
        int pageCount = count%limit==0?count/limit:count/limit+1;
        PageHelper<UserLoginHistoryVO> userLoginHistoryVOPageHelper = new PageHelper<>(count,pageCount,allUserLoginHistory);
        return new ResultVO(ResStatus.OK,"success",userLoginHistoryVOPageHelper);
    }
}
