package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.OutsidersMapper;
import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Outsiders;
import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.service.OutsidersService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OutsidersServiceImpl implements OutsidersService {
    @Autowired
    private OutsidersMapper outsidersMapper;
    private int limit=6;
    @Override
    public ResultVO findAllOutsiders(String outName,String outCar,int pageNum) {
        int count;
        int start = (pageNum-1)*limit;
        if("".equals(outName) && "".equals(outCar)){
           count = outsidersMapper.selectAll().size();
        }else{
            Example example = new Example(Outsiders.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("outName",outName);
            criteria.andLike("outCar",outCar);
            count = outsidersMapper.selectCountByExample(example);
        }
        List<Outsiders> allOutsiders = outsidersMapper.findAllOutsiders(outName,outCar,start, limit);
        int pageCount = count%limit==0 ? count/limit:count/limit+1;
        PageHelper<Outsiders> outsidersPageHelper = new PageHelper<>(count,pageCount,allOutsiders);
        return new ResultVO(ResStatus.OK,"success",outsidersPageHelper);
    }

    @Override
    public ResultVO addOutsiders(Outsiders outsiders) {
        int insert = outsidersMapper.insertSelective(outsiders);
        if(insert>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public ResultVO updateOutsiders(Outsiders outsiders) {
        int i = outsidersMapper.updateByPrimaryKeySelective(outsiders);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }
}
