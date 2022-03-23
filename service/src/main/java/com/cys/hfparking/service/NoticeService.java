package com.cys.hfparking.service;

import com.cys.hfparking.entity.Notice;
import com.cys.hfparking.vo.ResultVO;

public interface NoticeService {
    //查询最新发布的三条通知公告
    ResultVO selectNewNotice();
    //查询全部通知公告
    ResultVO selectAllNotice(int pageNum);
    //关键词搜索通知公告
    ResultVO selectNoticeByKeyword(String author,String content,int pageNum);
    //根据id下架通知公告
    ResultVO delNoticeByNid(int nid);
    //添加通知公告
    ResultVO addNotice(Notice notice);
}
