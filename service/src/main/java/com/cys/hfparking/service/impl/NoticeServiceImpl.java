package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.NoticeMapper;
import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Notice;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.service.NoticeService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    private int limit = 6;
    @Override
    public ResultVO selectNewNotice() {
        List<Notice> notices = noticeMapper.selectNewNotice();
        return new ResultVO(ResStatus.OK, "success", notices);
    }

    @Override
    public ResultVO selectAllNotice(int pageNum) {
        int start = (pageNum-1)*limit;
        List<Notice> all = noticeMapper.findAllNotice(start, limit);
        int count = noticeMapper.count().size();
        int pageCount = count%6==0 ? count/6:count/6+1;
        PageHelper<Notice> NoticePageHelper = new PageHelper<>(count,pageCount,all);
        return new ResultVO(ResStatus.OK,"success",NoticePageHelper);
    }

    @Override
    public ResultVO selectNoticeByKeyword(String author, String content, int pageNum) {
        int count;
        int start = (pageNum-1)*limit;
        if("".equals(author) && "".equals(content)){
            count = noticeMapper.count().size();
        }else{
            Example example = new Example(Notice.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("author",author);
            criteria.andLike("content",content);
            count = noticeMapper.selectCountByExample(example);
        }
        author = "%"+author+"%";
        content = "%"+content+"%";
        List<Notice> notices = noticeMapper.selectByKwNotice(author, content, start, limit);
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        PageHelper<Notice> pageHelper = new PageHelper<>(count,pageCount,notices);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO delNoticeByNid(int nid) {
        Notice notice = new Notice();
        notice.setNid(nid);
        notice.setStatus("0");
        int i = noticeMapper.updateByPrimaryKeySelective(notice);
        if(i>0){
            return new ResultVO(ResStatus.OK,"success",null);
        }else {
            return new ResultVO(ResStatus.NO,"error",null);
        }
    }

    @Override
    public ResultVO addNotice(Notice notice) {
        notice.setReleaseTime(new Date());
        int i = noticeMapper.insertUseGeneratedKeys(notice);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }
}
