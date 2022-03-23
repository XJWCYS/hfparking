package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.service.NoticeService;
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
@RequestMapping("/owners")
@Api(value = "业主信息管理功能",tags = "业主信息管理")
@CrossOrigin
public class OwnersController {
    @Resource
    private OwnersService ownersService;
    @GetMapping("/all")
    @ApiOperation("全部业主信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectAllOwner(@RequestHeader String token,@RequestParam(value = "pageNum") int pageNum){
        return ownersService.selectAllOwner(pageNum);
    }
    @GetMapping("/keyword")
    @ApiOperation("搜索业主信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "oname", value = "搜索业主的姓名"),
            @ApiImplicitParam(dataType = "string",name = "address", value = "搜索业主的地址"),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectOwnerByKeyword(@RequestHeader String token,
                                         @RequestParam(value = "oname",required = false) String oname,
                                         @RequestParam(value = "address",required = false) String address,
                                         @RequestParam(value = "pageNum") int pageNum){
        return ownersService.selectOwnerByKeyword(oname,address,pageNum);
    }

    @DeleteMapping("/del/{oid}")
    @ApiOperation("业主信息删除接口")
    public ResultVO delOwnerByOid(@PathVariable("oid") Integer oid,@RequestHeader String token){
        return ownersService.delOwnerByOid(oid);
    }
    @DeleteMapping("/delSel")
    @ApiOperation("业主信息批量删除接口")
    public ResultVO delOwnerByOids(@RequestParam Integer[] oids,@RequestHeader String token){
        return ownersService.delOwnerByOids(oids);
    }
    @PostMapping("/add")
    @ApiOperation("业主信息添加接口")
    public ResultVO addOwner(@RequestBody Owners owner, @RequestHeader String token){
        return ownersService.addOwner(owner);
    }
    @GetMapping("/excel")
    @ApiOperation("业主信息excel下载接口")
    public void excel(HttpServletResponse response){
        List<Owners> owners = ownersService.queryAllOwners();
        ExcelUtils.exportExcel(owners,"用户信息","sheet1",Owners.class,"业主信息.xls",response);
    }
    @GetMapping("/excel1")
    @ApiOperation("业主信息excel下载接口")
    public void excel1(HttpServletResponse response){
        List<Owners> owners = ownersService.queryAllOwners();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(new Date());
        String dateStr1=format1.format(new Date());
        ExcelUtils.exportExcel(owners,"业主信息("+dateStr+")","sheet1",Owners.class,"业主信息("+dateStr1+").xls",response);
    }
    @PostMapping("/addExcel")
    public ResultVO Excel(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<Owners> ownersList = ExcelUtils.importExcel(file,1,1,Owners.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println(ownersList.toString());
        return ownersService.addExcelOwners(ownersList);
    }
    @PostMapping("/addExcel1")
    public ResultVO Excel1(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<Owners> ownersList = ExcelUtils.importExcel(file,1,1,Owners.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        try{
            return ownersService.addExcelOwners1(ownersList);
        } catch (RuntimeException e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"fail","");
        }
    }
    @PostMapping("/update")
    @ApiOperation("业主信息修改接口")
    public ResultVO updateOwner(@RequestBody Owners owner, @RequestHeader String token){
        return ownersService.updateOwner(owner);
    }


}
