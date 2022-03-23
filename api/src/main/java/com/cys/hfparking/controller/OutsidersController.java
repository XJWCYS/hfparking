package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Outsiders;
import com.cys.hfparking.service.OutsidersService;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/outsiders")
@Api(value = "外来车辆信息管理功能",tags = "外来车辆信息管理")
@CrossOrigin
public class OutsidersController {
    @Resource
    private OutsidersService outsidersService;
    @GetMapping("/all")
    @ApiOperation("全部业主信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "outName", value = "搜索车辆的姓名"),
            @ApiImplicitParam(dataType = "string",name = "outCar", value = "搜索车辆的车牌号"),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectAllOwner(@RequestHeader String token,
                                   @RequestParam(value = "outName",required = false) String outName,
                                   @RequestParam(value = "outCar",required = false) String outCar,
                                   @RequestParam(value = "pageNum") int pageNum){
        return outsidersService.findAllOutsiders(outName,outCar,pageNum);
    }
    @PostMapping("/update")
    @ApiOperation("外来车辆信息修改接口")
    public ResultVO updateCar(@RequestBody Outsiders outsiders, @RequestHeader String token){
        return outsidersService.updateOutsiders(outsiders);
    }
    @PostMapping("/add")
    @ApiOperation("外来车辆信息添加接口")
    public ResultVO addCar(@RequestBody Outsiders outsiders, @RequestHeader String token){
        return outsidersService.addOutsiders(outsiders);
    }
}
