package com.goodsoft.yuanlin.service;

import com.goodsoft.yuanlin.entity.user.Users;
import com.goodsoft.yuanlin.util.resultentity.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * function 登陆注册模块业务逻辑接口Java类
 * <p>
 * date 2017-02-28
 *
 * @author 严彬荣
 */
public interface UserManageService {

    // 用户登陆接口
    public <T> T login(Users user);

    // 用户注册接口
    public Status register(Users user, HttpServletRequest req, MultipartFile[] files);

    //初始化系统权限管理接口
    public boolean rightsInitialization();

    //检查系统权限数据是否存在（方式服务器重启再次初始化权限数据）接口
    public boolean queryRights();

}
