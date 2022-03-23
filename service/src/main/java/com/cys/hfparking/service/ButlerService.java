package com.cys.hfparking.service;

import com.cys.hfparking.entity.Butler;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.vo.ResultVO;

import java.util.List;

public interface ButlerService {
    //分页根据关键词查询管家信息
    ResultVO selectButlerByKeyword(String butlerName,int pageNum);
    //查询正在值班的管家
    ResultVO findFree(int pageNum);
    //小程序查询正在值班的管家
    ResultVO findMiniFree(int pageNum);
    //根据id删除管家信息
    ResultVO delButlerByOid(int bid);
    //根据id删除管家信息
    ResultVO delButlerByOids(Integer[] bids);
    //查询所有的管家信息
    List<Butler> queryAllButler();
    //根据excel添加管家信息
    ResultVO addExcelButler(List<Butler> butlers);
    //添加业主信息
    ResultVO addButler(Butler butler);
    //添加业主信息
    ResultVO updateButler(Butler butler);
}
