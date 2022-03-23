package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.Parking;
import com.cys.hfparking.service.ParkingService;
import com.cys.hfparking.service.UserService;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/parking")
@Api(value = "车位的增删改查功能",tags = "车位管理")
@CrossOrigin
public class ParkingController {
    @Resource
    private ParkingService parkingService;
    @GetMapping("/all")
    @ApiOperation("全部车位接⼝")
    public ResultVO findAll(@RequestHeader String token,@RequestParam int pageNum){
        return parkingService.findAll(pageNum);
    }
    @GetMapping("/free")
    @ApiOperation("空闲车位接⼝")
    public ResultVO findFree(@RequestHeader String token,@RequestParam int pageNum){
        return parkingService.findFree(pageNum);
    }
    @DeleteMapping("/del/{pid}")
    @ApiOperation("车位信息删除接口")
    public ResultVO delParking(@PathVariable("pid") Integer pid,@RequestHeader String token){
        return parkingService.delParking(pid);
    }
    @PostMapping("/add")
    @ApiOperation("车位信息添加接口")
    public ResultVO addParking(@RequestBody Parking parking, @RequestHeader String token){
        return parkingService.addParking(parking);
    }
    @GetMapping("/find/{pid}")
    @ApiOperation("根据id查询车位信息接口")
    public ResultVO selectByPidParking(@PathVariable("pid") Integer pid,@RequestHeader String token){
        return parkingService.selectByPidParking(pid);
    }
    @PostMapping("/update")
    @ApiOperation("车位信息修改接口")
    public ResultVO updateOwner(@RequestBody Parking parking, @RequestHeader String token){
        return parkingService.updateParking(parking);
    }
    @GetMapping("/count")
    @ApiOperation("车位数量接口")
    public ResultVO count(@RequestHeader String token){
        return parkingService.findAllNum();
    }
}
