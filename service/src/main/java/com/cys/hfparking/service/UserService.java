package com.cys.hfparking.service;

import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.User;
import com.cys.hfparking.vo.ResultVO;

import java.util.List;

public interface UserService {
    //用户登录
    ResultVO checkLogin(String username,String password);
    //查询权限为2的用户
    ResultVO findUserByKw(String username,int pageNum);
    //根据id删除用户信息
    ResultVO delUserByid(int id);
    //根据id删除用户信息
    ResultVO delUserByids(Integer[] ids);
    //添加业主信息
    ResultVO addUser(User user);
    //查询所有的用户信息
    List<User> queryAllUser();
    //根据excel添加用户信息
    ResultVO addExcelUser(List<User> users);
    //根据excel添加用户信息
    ResultVO addExcelUser1(List<User> users);
    //修改用户信息
    ResultVO updateUser(User user);
    //重置用户密码为123456
    ResultVO backUser(User user);
    //用户修改密码
    ResultVO updatePassword(int userId,String oldPassword,String newPassword);
    //用户修改用户名
    ResultVO updateUsername(int userId,String username);
    //普通用户注册
    ResultVO regist(String username,String password);
}
