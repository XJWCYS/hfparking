package com.cys.hfparking.service;

import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OwnersService {
    //分页查询全部业主信息
    ResultVO selectAllOwner(int pageNum);
    //关键词搜索业主
    ResultVO selectOwnerByKeyword(String oname,String address,int pageNum);
    //根据id删除业主信息
    ResultVO delOwnerByOid(int oid);
    //根据id删除业主信息
    ResultVO delOwnerByOids(Integer[] oids);
    //添加业主信息
    ResultVO addOwner(Owners owner);
    //查询所有的业主信息
    List<Owners> queryAllOwners();
    //根据excel添加业主信息
    ResultVO addExcelOwners(List<Owners> owners);
    //根据excel添加业主信息
    ResultVO addExcelOwners1(List<Owners> owners);
    //修改业主信息
    ResultVO updateOwner(Owners owner);
}
