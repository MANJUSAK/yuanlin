package com.goodsoft.yuanlin.controller;

import com.goodsoft.yuanlin.entity.user.Users;
import com.goodsoft.yuanlin.service.UserManageService;
import com.goodsoft.yuanlin.util.resultentity.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * function 登陆注册模块访问入口java类
 * <p>
 * date 2017.02.28
 *
 * @author 严彬荣
 */
@Controller
public class UserManageController {

    @Resource
    private UserManageService service;


    /**
     * 用户登陆访问接口
     *
     * @param user 用户登录信息
     * @return 登录结果
     */
    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8", method = {RequestMethod.POST})
    public Object Login(Users user) {
        return this.service.login(user);
    }


    /**
     * 用户注册信息访问接口
     *
     * @param user 用户注册信息，req http请求，files 用户文件
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = "/register", produces = "application/json;charset=utf-8", method = {RequestMethod.POST})
    public Status register(Users user, HttpServletRequest req, @RequestParam("files") MultipartFile[] files) {
        return this.service.register(user, req, files);
    }

}
