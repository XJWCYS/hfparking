package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.ButlerMapper;
import com.cys.hfparking.entity.Butler;
import com.cys.hfparking.entity.Outsiders;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.service.ButlerService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ButlerServiceImpl implements ButlerService {
    @Autowired
    private ButlerMapper butlerMapper;
    private int limit = 6;
    @Override
    public ResultVO selectButlerByKeyword(String butlerName, int pageNum) {
        int count;
        int start = (pageNum-1)*limit;
        if("".equals(butlerName)){
            count = butlerMapper.selectAll().size();
        }else{
            Example example = new Example(Butler.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("butlerName",butlerName);
            count = butlerMapper.selectCountByExample(example);
        }
        butlerName = "%"+butlerName+"%";
        List<Butler> butlers = butlerMapper.selectByKwButler(butlerName, start, limit);
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        PageHelper<Butler> pageHelper = new PageHelper<>(count,pageCount,butlers);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO findFree(int pageNum) {
            Example example = new Example(Butler.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("status",1);
            List<Butler> butlers = butlerMapper.selectByExample(example);
            int start = (pageNum-1)*limit;
            List<Butler> free = butlerMapper.findFree(start, limit);
            int count = butlers.size();
            int pageCount = count%6==0 ? count/6:count/6+1;
            PageHelper<Butler> parkingPageHelper = new PageHelper<>(count,pageCount,free);
            return new ResultVO(ResStatus.OK,"success",parkingPageHelper);
    }

    @Override
    public ResultVO findMiniFree(int pageNum) {
        Example example = new Example(Butler.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        List<Butler> butlers = butlerMapper.selectByExample(example);
        limit = 15;
        int start = (pageNum-1)*limit;
        List<Butler> free = butlerMapper.findFree(start, limit);
        int count = butlers.size();
        int pageCount = count%limit==0 ? count/limit:count/limit+1;
        PageHelper<Butler> parkingPageHelper = new PageHelper<>(count,pageCount,free);
        return new ResultVO(ResStatus.OK,"success",parkingPageHelper);
    }

    @Override
    public ResultVO delButlerByOid(int bid) {
        int i = butlerMapper.deleteByPrimaryKey(bid);
        if(i>0){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO delButlerByOids(Integer[] bids) {
        int i = butlerMapper.delSel(bids);
        if(i==bids.length){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public List<Butler> queryAllButler() {
        List<Butler> butlers = butlerMapper.selectAll();
        return butlers;
    }

    @Override
    public ResultVO addExcelButler(List<Butler> butlers) {
        int i = butlerMapper.addExcelButler(butlers);
        if(i==butlers.size()){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public ResultVO addButler(Butler butler) {
        int i = butlerMapper.insertUseGeneratedKeys(butler);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public ResultVO updateButler(Butler butler) {
        int i = butlerMapper.updateByPrimaryKey(butler);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }
}
