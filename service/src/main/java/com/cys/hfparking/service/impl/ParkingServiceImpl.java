package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.ParkingMapper;
import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.service.ParkingService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.utils.ParkingNum;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private ParkingMapper parkingMapper;
    private int limit = 6;
    @Override
    public ResultVO findAll(int pageNum) {
        int start = (pageNum-1)*limit;
        List<Parking> all = parkingMapper.findAll(start, limit);
        List<Parking> parkings1 = parkingMapper.selectAll();
        int count = parkings1.size();
        int pageCount = count%6==0 ? count/6:count/6+1;
        PageHelper<Parking> parkingPageHelper = new PageHelper<>(count,pageCount,all);
        return new ResultVO(ResStatus.OK,"success",parkingPageHelper);
    }

    @Override
    public ResultVO findFree(int pageNum) {
        Example example = new Example(Parking.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parkingStop",1);
        List<Parking> parkings1 = parkingMapper.selectByExample(example);
        int start = (pageNum-1)*limit;
        List<Parking> free = parkingMapper.findFree(start, limit);
        int count = parkings1.size();
        int pageCount = count%6==0 ? count/6:count/6+1;
        PageHelper<Parking> parkingPageHelper = new PageHelper<>(count,pageCount,free);
        return new ResultVO(ResStatus.OK,"success",parkingPageHelper);
    }

    @Override
    public ResultVO delParking(int pid) {
        int i = parkingMapper.deleteByPrimaryKey(pid);
        if(i>0){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO addParking(Parking parking) {
        int i = parkingMapper.insertUseGeneratedKeys(parking);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public ResultVO selectByPidParking(int pid) {
        Parking parking = parkingMapper.selectByPrimaryKey(pid);
        List<Parking> list = new ArrayList<>();
        list.add(parking);
        return new ResultVO(ResStatus.OK,"success",list);
    }

    @Override
    public ResultVO updateParking(Parking parking) {
        int i = parkingMapper.updateByPrimaryKey(parking);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }

    @Override
    public ResultVO findAllNum() {
        int count = parkingMapper.selectAll().size();
        Example example = new Example(Parking.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parkingStop",1);
        int free = parkingMapper.selectCountByExample(example);
        int busy = count-free;
        int percentage = (free*100)/count;
        //小型车位数据
        Example example1 = new Example(Parking.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("parkingCategory",1);
        int aCount = parkingMapper.selectCountByExample(example1);
        criteria1.andEqualTo("parkingStop",1);
        int aFree = parkingMapper.selectCountByExample(example1);
        int aPercentage = (aFree*100)/aCount;
        //中型车位数据
        Example example2 = new Example(Parking.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("parkingCategory",2);
        int bCount = parkingMapper.selectCountByExample(example2);
        criteria2.andEqualTo("parkingStop",1);
        int bFree = parkingMapper.selectCountByExample(example2);
        int bPercentage = (bFree*100)/bCount;
        //小型车位数据
        Example example3 = new Example(Parking.class);
        Example.Criteria criteria3 = example3.createCriteria();
        criteria3.andEqualTo("parkingCategory",3);
        int cCount = parkingMapper.selectCountByExample(example3);
        criteria3.andEqualTo("parkingStop",1);
        int cFree = parkingMapper.selectCountByExample(example3);
        int cPercentage = (cFree*100)/cCount;


        System.out.println(percentage);
        return new ResultVO(ResStatus.OK,"success",new ParkingNum(count,free,busy,percentage,
                                                                        aCount,aFree,aPercentage,
                                                                        bCount,bFree,bPercentage,
                                                                        cCount,cFree,cPercentage));
    }
}
