package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.CarMapper;
import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.service.CarService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.SQLException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    private int limit = 6;//每页记录数
    @Override
    public ResultVO findAllCar(int pageNum) {
        int count = carMapper.selectAll().size();
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        int start = (pageNum-1)*limit;
        List<Car> allCar = carMapper.findAllCar(start, limit);
        PageHelper<Car> pageHelper = new PageHelper<>(count,pageCount,allCar);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO delCarByOid(int cid) {
        int i = carMapper.deleteByPrimaryKey(cid);
        if(i>0){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO selectOwnerByKeyword(String ownerName, String carNum, int pageNum) {
        int count;
        int start = (pageNum-1)*limit;
        if("".equals(ownerName) && "".equals(carNum)){
            count = carMapper.selectAll().size();
        }else{
            Example example = new Example(Car.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("ownerName",ownerName);
            criteria.andLike("carNum",carNum);
            count = carMapper.selectCountByExample(example);
        }
        ownerName = "%"+ownerName+"%";
        carNum = "%"+carNum+"%";
        List<Car> cars = carMapper.findByKwCar(ownerName, carNum, start, limit);
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        PageHelper<Car> pageHelper = new PageHelper<>(count,pageCount,cars);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO delCarByCids(Integer[] cids) {
        int i = carMapper.delSel(cids);
        if(i==cids.length){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO addCar(Car car) {
        int i = carMapper.insertUseGeneratedKeys(car);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public ResultVO updateCar(Car car) {
        int i = carMapper.updateByPrimaryKey(car);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }

    @Override
    public List<Car> queryAllCar() {
        List<Car> cars = carMapper.selectAll();
        return cars;
    }

    @Override
    public ResultVO addExcelCars(List<Car> cars) {
        int i = carMapper.addExcelCars(cars);
        if(i==cars.size()){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO addExcelCars1(List<Car> cars) {
        int cid = 0;
        List<Car> cars1 = carMapper.selectAll();
        for (int i = 0; i < cars1.size(); i++) {
            cid = cars1.get(i).getCid();
            carMapper.deleteByPrimaryKey(cid);
        }

            int i = carMapper.addExcelCars(cars);
            if(i==cars.size()){
                return new ResultVO(ResStatus.OK,"添加成功",null);
            }else {
                return new ResultVO(ResStatus.NO,"添加失败",null);
            }


    }

    @Override
    public ResultVO findAllCountCar() {
        int count = carMapper.selectAll().size();
        return new ResultVO(ResStatus.OK,"success",count);
    }
}
