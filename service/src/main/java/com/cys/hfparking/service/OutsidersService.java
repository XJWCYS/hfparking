package com.cys.hfparking.service;

import com.cys.hfparking.entity.Outsiders;
import com.cys.hfparking.vo.ResultVO;

public interface OutsidersService {
    //查询外来人员信息
    ResultVO findAllOutsiders(String outName,String outCar,int pageNum);
    //添加外来人员信息
    ResultVO addOutsiders(Outsiders outsiders);
    //修改外来人员信息
    ResultVO updateOutsiders(Outsiders outsiders);
}
