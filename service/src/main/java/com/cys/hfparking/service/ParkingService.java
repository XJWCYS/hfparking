package com.cys.hfparking.service;

import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestParam;

public interface ParkingService {
    //查询所有停车位
    ResultVO findAll(int pageNum);
    //查询空闲车位
    ResultVO findFree(int pageNum);
    //根据pid删除车位
    ResultVO delParking(int pid);
    //添加车位
    ResultVO addParking(Parking parking);
    //根据id查询车位
    ResultVO selectByPidParking(int pid);
    //修改车位信息
    ResultVO updateParking(Parking parking);
    //查询所有车位数量信息
    ResultVO findAllNum();
}
