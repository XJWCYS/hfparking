package com.cys.hfparking.dao;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Notice;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoticeMapper extends GeneralDao<Notice> {
    //获取最新的通知公告
    List<Notice> selectNewNotice();
    List<Notice> count();
    List<Notice> findAllNotice(int start, int limit);
    List<Notice> selectByKwNotice(@Param("author") String author,
                                 @Param("content") String content,
                                 @Param("start") int start,
                                 @Param("limit") int limit);
}