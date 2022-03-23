package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Butler;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.service.ButlerService;
import com.cys.hfparking.service.CarService;
import com.cys.hfparking.utils.ExcelUtils;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/butler")
@Api(value = "车管家管理功能",tags = "车管家管理")
@CrossOrigin
public class ButlerController {
    @Resource
    private ButlerService butlerService;
    @GetMapping("/keyword")
    @ApiOperation("搜索车管家信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "butlerName", value = "搜索车管家的姓名"),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectOwnerByKeyword(@RequestHeader String token,
                                         @RequestParam(value = "butlerName",required = false) String butlerName,
                                         @RequestParam(value = "pageNum") int pageNum){
        return butlerService.selectButlerByKeyword(butlerName,pageNum);
    }
    @GetMapping("/free")
    @ApiOperation("值班车管家信息接⼝")
    public ResultVO findFree(@RequestHeader String token,@RequestParam int pageNum){
        return butlerService.findFree(pageNum);
    }
    @GetMapping("/mini")
    @ApiOperation("值班车管家信息接⼝")
    public ResultVO findMiniFree(@RequestHeader String token,@RequestParam int pageNum){
        return butlerService.findMiniFree(pageNum);
    }
    @DeleteMapping("/del/{bid}")
    @ApiOperation("车管家信息删除接口")
    public ResultVO delButlerByOid(@PathVariable("bid") Integer bid,@RequestHeader String token){
        return butlerService.delButlerByOid(bid);
    }
    @DeleteMapping("/delSel")
    @ApiOperation("车管家信息批量删除接口")
    public ResultVO delOwnerByOids(@RequestParam Integer[] bids,@RequestHeader String token){
        return butlerService.delButlerByOids(bids);
    }
    @GetMapping("/excel")
    @ApiOperation("车管家信息excel下载接口")
    public void excel(HttpServletResponse response){
        List<Butler> butlers = butlerService.queryAllButler();
        ExcelUtils.exportExcel(butlers,"车管家信息","sheet1",Butler.class,"车管家信息.xls",response);
    }
    @PostMapping("/addExcel")
    @ApiOperation("车管家信息excel批量增加接口")
    public ResultVO Excel(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<Butler> butlers = ExcelUtils.importExcel(file,1,1,Butler.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println(butlers.toString());
        return butlerService.addExcelButler(butlers);
    }
    @PostMapping("/update")
    @ApiOperation("车管家信息修改接口")
    public ResultVO updateOwner(@RequestBody Butler butler, @RequestHeader String token){
        return butlerService.updateButler(butler);
    }
    @PostMapping("/add")
    @ApiOperation("车管家信息添加接口")
    public ResultVO addOwner(@RequestBody Butler butler, @RequestHeader String token){
        return butlerService.addButler(butler);
    }


    }
