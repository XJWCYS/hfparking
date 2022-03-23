package com.cys.hfparking.controller;

import com.cys.hfparking.service.UserLoginHistoryService;
import com.cys.hfparking.service.UserService;
import com.cys.hfparking.utils.IpUtils;
import com.cys.hfparking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userLoginHistory")
@Api(value = "系统登录日志",tags = "登录日志管理")
@CrossOrigin
public class UserLoginHistoryController {
    @Resource
    private UserLoginHistoryService userLoginHistoryService;
    @PostMapping("/add")
    @ApiOperation("⽤户登录添加日志接⼝")
    public void add(HttpServletRequest request,@RequestParam int uid){
        String ip = IpUtils.getIpAddr(request);
        userLoginHistoryService.addLoginHistory(ip,uid);
    }
    @GetMapping("/all")
    @ApiOperation("⽤户登录添加日志接⼝")
    public ResultVO findAllUserLoginHistory(@RequestHeader String token,@RequestParam int pageNum){
        return userLoginHistoryService.findAllLoginHistory(pageNum);
    }

}
