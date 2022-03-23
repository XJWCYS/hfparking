package com.cys.hfparking.service.impl;

import com.cys.hfparking.dao.OwnersMapper;
import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.service.OwnersService;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OwnersServiceImpl implements OwnersService {
    @Autowired
    private OwnersMapper ownersMapper;
    private int limit = 6;//每页记录数
    @Override
    public ResultVO selectAllOwner(int pageNum) {
//        int limit = 6;
        int count = ownersMapper.selectAll().size();
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        int start = (pageNum-1)*limit;
        List<Owners> ownersVOS = ownersMapper.selectAllOwner(start,limit);
        PageHelper<Owners> pageHelper = new PageHelper<>(count,pageCount,ownersVOS);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO selectOwnerByKeyword(String oname, String address, int pageNum) {
        int count;
        int start = (pageNum-1)*limit;

        if("".equals(oname) && "".equals(address)){
            count = ownersMapper.selectAll().size();
        }else{
            Example example = new Example(Owners.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("oname",oname);
            criteria.andLike("address",address);
            count = ownersMapper.selectCountByExample(example);
        }
        oname = "%"+oname+"%";
        address = "%"+address+"%";
        List<Owners> ownersVOS = ownersMapper.selectByKwOwner(oname,address,start,limit);
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        PageHelper<Owners> pageHelper = new PageHelper<>(count,pageCount,ownersVOS);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO delOwnerByOid(int oid) {
        int i = ownersMapper.deleteByPrimaryKey(oid);
        if(i>0){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO delOwnerByOids(Integer[] oids) {
        int i = ownersMapper.delSel(oids);
        if(i==oids.length){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO addOwner(Owners owner) {
        int i = ownersMapper.insertUseGeneratedKeys(owner);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public List<Owners> queryAllOwners() {
        List<Owners> owners = ownersMapper.selectAll();
        return owners;
    }

    @Override
    public ResultVO addExcelOwners(List<Owners> owners) {
        int i = ownersMapper.addExcelOwners(owners);
        if(i==owners.size()){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO addExcelOwners1(List<Owners> owners) {
        int oid = 0;
        List<Owners> owners1 = ownersMapper.selectAll();
        for (int i = 0; i < owners1.size(); i++) {
            oid = owners1.get(i).getOid();
            ownersMapper.deleteByPrimaryKey(oid);
        }
            int i = ownersMapper.addExcelOwners(owners);
            if(i==owners.size()){
                return new ResultVO(ResStatus.OK,"添加成功",null);
            }else {
                return new ResultVO(ResStatus.NO,"添加失败",null);
            }

    }

    @Override
    public ResultVO updateOwner(Owners owner) {
        int i = ownersMapper.updateByPrimaryKey(owner);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }
}
