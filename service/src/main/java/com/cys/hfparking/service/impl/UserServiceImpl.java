package com.cys.hfparking.service.impl;

import com.cys.hfparking.entity.Butler;
import com.cys.hfparking.entity.Owners;
import com.cys.hfparking.entity.User;
import com.cys.hfparking.service.UserService;
import com.cys.hfparking.dao.UserMapper;
import com.cys.hfparking.utils.MD5Utils;
import com.cys.hfparking.utils.PageHelper;
import com.cys.hfparking.vo.ResStatus;
import com.cys.hfparking.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private int limit=6;
    @Override
    public ResultVO checkLogin(String username, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        List<User> users = userMapper.selectByExample(example);
        if(users.size() == 0){
            return new ResultVO(ResStatus.NO,"用户名错误",null);
        }else{
            String md5Pwd = MD5Utils.md5(password);
            if(md5Pwd.equals(users.get(0).getPassword())){
                //如果登录验证成功，则需要生成令牌token（token就是按照特定规则生成的字符串）
                //使用jwt规则生成token字符串
                JwtBuilder builder = Jwts.builder();
                HashMap<String,Object> map = new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                String token = builder.setSubject(username)  //主题，就是token中携带的数据
                        .setIssuedAt(new Date())     //设置token的生成时间
                        .setId(users.get(0).getId() + "")//设置用户id为token  id
                        .setClaims(map)             //map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                        .signWith(SignatureAlgorithm.HS256,"cysxjw")
                        .compact();
                return new ResultVO(ResStatus.OK,token,users.get(0));
            }else{
                return new ResultVO(ResStatus.NO,"密码错误",null);
            }
        }
    }

    @Override
    public ResultVO findUserByKw(String username, int pageNum) {
        username="%"+username+"%";
        int start = (pageNum-1)*limit;
        List<User> users = userMapper.findUserByKw(username, start, limit);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("access",1);
//        criteria.andEqualTo("access",2);
        int count = userMapper.selectCountByExample(example);
        int pageCount = count%limit== 0? count/limit:count/limit+1;
        PageHelper<User> pageHelper = new PageHelper<>(count,pageCount,users);
        return new ResultVO(ResStatus.OK,"success",pageHelper);
    }

    @Override
    public ResultVO delUserByid(int id) {
        int i = userMapper.deleteByPrimaryKey(id);
        if(i>0){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO delUserByids(Integer[] ids) {
        int i = userMapper.delSel(ids);
        if(i==ids.length){
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"删除失败",null);
        }
    }

    @Override
    public ResultVO addUser(User user) {
        String password = user.getPassword();
        String md5Pwd = MD5Utils.md5(password);
        user.setPassword(md5Pwd);
        int i = userMapper.insertUseGeneratedKeys(user);
        if(i>0){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    public List<User> queryAllUser() {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("access",1);
        List<User> users = userMapper.selectByExample(example);
//        List<User> users1 = userMapper.selectAll();
//        for (int i = 0; i < users.size(); i++) {
//            String password = users.get(i).getPassword();
//            String md5Pwd = MD5Utils.convertMD5(MD5Utils.convertMD5(password));
//            users.get(i).setPassword(md5Pwd);
//        }
        return users;
    }

    @Override
    public ResultVO addExcelUser(List<User> users) {

        System.out.println(users.toString());
        int i = userMapper.addExcelUsers(users);
        if(i==users.size()){
            return new ResultVO(ResStatus.OK,"添加成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"添加失败",null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO addExcelUser1(List<User> users) {

        int uid = 0;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("access",1);
        List<User> users1 = userMapper.selectByExample(example);
        for (int i = 0; i < users1.size(); i++) {
            uid = users1.get(i).getId();
            userMapper.deleteByPrimaryKey(uid);
        }
            int i = userMapper.addExcelUsers(users);
            if(i==users.size()){
                return new ResultVO(ResStatus.OK,"添加成功",null);
            }else {
                return new ResultVO(ResStatus.NO,"添加失败",null);
            }

    }

    @Override
    public ResultVO updateUser(User user) {
//        String password = user.getPassword();
//        if(password.length()>16){
//            String md5Pwd = MD5Utils.md5(password);
//            user.setPassword(md5Pwd);
//        }
        int i = userMapper.updateByPrimaryKey(user);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }

    @Override
    public ResultVO backUser(User user) {
        String md5Pwd = MD5Utils.md5("123456");
        user.setPassword(md5Pwd);
        int i = userMapper.updateByPrimaryKey(user);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }

    @Override
    public ResultVO updatePassword(int userId,String oldPassword, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        String md5OldPwd = MD5Utils.md5(oldPassword);
        if(md5OldPwd.equals(user.getPassword())){
            String md5NewPwd = MD5Utils.md5(newPassword);
            user.setPassword(md5NewPwd);
            int i = userMapper.updateByPrimaryKey(user);
            if(i>0){
                return new ResultVO(ResStatus.OK,"修改成功",null);
            }else {
                return new ResultVO(ResStatus.NO,"修改失败",null);
            }
        }else {
            return new ResultVO(ResStatus.NO,"原密码不正确，请重新输入！",null);
        }

    }

    @Override
    public ResultVO updateUsername(int userId,String username) {
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"修改失败",null);
        }
    }

    @Override
    public ResultVO regist(String username, String password) {
        //锁
        synchronized (this) {
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", username);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() > 0) {
                return new ResultVO(ResStatus.NO, "用户名已存在", null);
            } else {
                String md5Pwd = MD5Utils.md5(password);
                User user = new User();
                user.setUsername(username);
                user.setPassword(md5Pwd);
                user.setAccess(3);
                int i = userMapper.insert(user);
                if (i > 0) {
                    return new ResultVO(ResStatus.OK, "注册成功", null);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败", null);
                }
            }
        }
        
    }
}
