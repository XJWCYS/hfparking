package com.cys.hfparking.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;

public class User {
    /**
     * 用户id
     */
    @Id
    @Excel(name = "用户编号", orderNum = "0")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    @Excel(name = "用户姓名", orderNum = "1")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    @Excel(name = "用户密码(只能查看加密后的密码)", orderNum = "2", width = 25)
    private String password;

    /**
     * 用户权限 1：超级管理员 2：普通车管家
     */
    @Column(name = "access")
    @Excel(name = "用户权限 1:超级管理员 2:普通管理员", orderNum = "3", width = 25)
    private Integer access;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户权限 1：超级管理员 2：普通车管家
     *
     * @return access - 用户权限 1：超级管理员 2：普通车管家
     */
    public Integer getAccess() {
        return access;
    }

    /**
     * 设置用户权限 1：超级管理员 2：普通车管家
     *
     * @param access 用户权限 1：超级管理员 2：普通车管家
     */
    public void setAccess(Integer access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", access=" + access +
                '}';
    }
}