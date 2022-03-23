package com.cys.hfparking.controller;

import com.cys.hfparking.entity.Car;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.User;
import com.cys.hfparking.service.UserService;
import com.cys.hfparking.utils.ExcelUtils;
import com.cys.hfparking.utils.MD5Utils;
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
@RequestMapping("/user")
@Api(value = "登录、注册以及用户信息的修改等功能",tags = "用户管理")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/login")
    @ApiOperation("⽤户登录接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username", value = "⽤户登录账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password", value = "⽤户登录密码",required = true)
    })
    public ResultVO login(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password){
        return userService.checkLogin(username,password);
    }
    @GetMapping("/keyword")
    @ApiOperation("搜索用户信息接⼝")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "string",name = "username", value = "搜索用户的姓名"),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true)
    })
    public ResultVO selectUserByKeyword(@RequestHeader String token,
                                         @RequestParam(value = "username",required = false) String username,
                                         @RequestParam(value = "pageNum") int pageNum){
        return userService.findUserByKw(username,pageNum);
    }
    @DeleteMapping("/del/{id}")
    @ApiOperation("用户信息删除接口")
    public ResultVO delUserByOid(@PathVariable("id") Integer id,@RequestHeader String token){
        return userService.delUserByid(id);
    }
    @DeleteMapping("/delSel")
    @ApiOperation("用户信息批量删除接口")
    public ResultVO delUserByids(@RequestParam Integer[] ids,@RequestHeader String token){
        return userService.delUserByids(ids);
    }
    @PostMapping("/add")
    @ApiOperation("用户信息添加接口")
    public ResultVO addUser(@RequestBody User user, @RequestHeader String token){
        return userService.addUser(user);
    }
    @GetMapping("/excel")
    @ApiOperation("业主信息excel下载接口")
    public void excel(HttpServletResponse response){
        List<User> users = userService.queryAllUser();
        ExcelUtils.exportExcel(users,"用户信息","sheet1",User.class,"用户信息.xls",response);
    }
    @GetMapping("/excel1")
    @ApiOperation("业主信息excel下载接口")
    public void excel1(HttpServletResponse response){
        List<User> users = userService.queryAllUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(new Date());
        String dateStr1=format1.format(new Date());
        System.out.println(dateStr1);
        ExcelUtils.exportExcel(users,"用户信息("+dateStr+")","sheet1",User.class,"用户信息("+dateStr1+").xls",response);
    }
    @PostMapping("/addExcel")
    public ResultVO Excel(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<User> userList = ExcelUtils.importExcel(file,1,1,User.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println(userList.toString());
        for (User user:userList) {
            String password = user.getPassword();
            String md5Pwd = MD5Utils.md5(password);
            user.setPassword(md5Pwd);
            System.out.println(user);
        }
        return userService.addExcelUser(userList);
    }
    @PostMapping("/addExcel1")
    public ResultVO Excel1(@RequestParam("file") MultipartFile file, @RequestHeader String token){

        List<User> users = ExcelUtils.importExcel(file, 1, 1, User.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
//        System.out.println(ownersList.toString());
        try{
            return userService.addExcelUser1(users);
        } catch (RuntimeException e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"fail","");
        }
    }
    @PostMapping("/update")
    @ApiOperation("用户信息修改接口")
    public ResultVO updateUser(@RequestBody User user, @RequestHeader String token){
        return userService.updateUser(user);
    }
    @PostMapping("/back")
    @ApiOperation("用户密码重置接口")
    public ResultVO backUser(@RequestBody User user, @RequestHeader String token){
        return userService.backUser(user);
    }
    @PostMapping("/updatePassword")
    @ApiOperation("用户密码修改接口")
    public ResultVO updatePassword(@RequestParam int userId,
                                   @RequestParam String oldPassword,
                                   @RequestParam String newPassword,
                                   @RequestHeader String token){
        return userService.updatePassword(userId,oldPassword,newPassword);
    }
    @PostMapping("/updateUsername")
    @ApiOperation("用户名修改接口")
    public ResultVO updateUsername(@RequestParam int userId,
                                   @RequestParam String username,
                                   @RequestHeader String token){
        return userService.updateUsername(userId,username);
    }
    @PostMapping("/regist")
    @ApiOperation("用户名修改接口")
    public ResultVO registUser(@RequestParam String username,
                                   @RequestParam String password){
        return userService.regist(username,password);
    }
}
