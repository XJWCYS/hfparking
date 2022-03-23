package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.service.CarService;
import com.cys.hfparking.service.OwnersService;
import com.cys.hfparking.utils.ExcelUtils;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/car")
@Api(value = "车辆管理功能",tags = "车辆管理")
@CrossOrigin
public class CarController {
    @Resource
    private CarService carService;
    @GetMapping("/all")
    @ApiOperation("全部车辆信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO findAllCar(@RequestHeader String token, @RequestParam(value = "pageNum") int pageNum){
        return carService.findAllCar(pageNum);
    }
    @DeleteMapping("/del/{cid}")
    @ApiOperation("车辆删除接口")
    public ResultVO delOwnerByOid(@PathVariable("cid") Integer cid,@RequestHeader String token){
        return carService.delCarByOid(cid);
    }
    @GetMapping("/keyword")
    @ApiOperation("搜索车辆信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "ownerName", value = "搜索车辆的姓名"),
            @ApiImplicitParam(dataType = "string",name = "carNum", value = "搜索车辆的车牌号"),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectOwnerByKeyword(@RequestHeader String token,
                                         @RequestParam(value = "ownerName",required = false) String ownerName,
                                         @RequestParam(value = "carNum",required = false) String carNum,
                                         @RequestParam(value = "pageNum") int pageNum){
        return carService.selectOwnerByKeyword(ownerName,carNum,pageNum);
    }
    @DeleteMapping("/delSel")
    @ApiOperation("车辆信息批量删除接口")
    public ResultVO delOwnerByOids(@RequestParam Integer[] cids,@RequestHeader String token){
        return carService.delCarByCids(cids);
    }
    @PostMapping("/add")
    @ApiOperation("车辆信息添加接口")
    public ResultVO addCar(@RequestBody Car car, @RequestHeader String token){
        return carService.addCar(car);
    }
    @PostMapping("/update")
    @ApiOperation("车辆信息修改接口")
    public ResultVO updateCar(@RequestBody Car car, @RequestHeader String token){
        return carService.updateCar(car);
    }


    @GetMapping("/excel")
    @ApiOperation("车辆信息excel下载接口")
    public void excel(HttpServletResponse response){
        List<Car> cars = carService.queryAllCar();
        ExcelUtils.exportExcel(cars,"车辆信息","sheet1",Car.class,"车辆信息.xls",response);
    }
    @GetMapping("/excel1")
    @ApiOperation("车辆信息excel下载接口")
    public void excel1(HttpServletResponse response){
        List<Car> cars = carService.queryAllCar();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(new Date());
        String dateStr1=format1.format(new Date());
        ExcelUtils.exportExcel(cars,"车辆信息("+dateStr+")","sheet1",Car.class,"车辆信息("+dateStr1+").xls",response);
    }
    @PostMapping("/addExcel")
    public ResultVO Excel(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<Car> cars = ExcelUtils.importExcel(file, 1, 1, Car.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
//        System.out.println(ownersList.toString());
        return carService.addExcelCars(cars);
    }
    @PostMapping("/addExcel1")
    public ResultVO Excel1(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<Car> cars = ExcelUtils.importExcel(file, 1, 1, Car.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
//        System.out.println(ownersList.toString());
        try{
            return carService.addExcelCars1(cars);
        } catch (RuntimeException e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"fail","");
        }

    }
    @GetMapping("/count")
    @ApiOperation("车辆数量接口")
    public ResultVO count(@RequestHeader String token){
        return carService.findAllCountCar();
    }
}
