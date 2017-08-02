package com.goodsoft.yuanlin.service.servicelmpl;

import com.goodsoft.yuanlin.controller.formfilter.UserFromFilterUtil;
import com.goodsoft.yuanlin.dao.Filedao;
import com.goodsoft.yuanlin.dao.UserManageDao;
import com.goodsoft.yuanlin.entity.file.FileData;
import com.goodsoft.yuanlin.entity.user.Grade;
import com.goodsoft.yuanlin.entity.user.Rights;
import com.goodsoft.yuanlin.entity.user.UserInfo;
import com.goodsoft.yuanlin.entity.user.Users;
import com.goodsoft.yuanlin.service.SystemPrivilegesService;
import com.goodsoft.yuanlin.service.UserManageService;
import com.goodsoft.yuanlin.util.CreateMD5Util;
import com.goodsoft.yuanlin.util.UUIDUtil;
import com.goodsoft.yuanlin.util.UserFileUploadUtil;
import com.goodsoft.yuanlin.util.resultentity.Result;
import com.goodsoft.yuanlin.util.resultentity.Status;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * function 登陆注册模块业务逻辑Java类
 *
 * @author 严彬荣
 */
@SuppressWarnings("ALL")
@Service
public class UserManageServicelmpl implements UserManageService {

    @Resource
    private UserManageDao dao;
    @Resource
    private Filedao filedao;
    @Resource
    private SystemPrivilegesService privilegesService;
    //实例化日志管理工具类
    private Logger logger = Logger.getLogger(UserManageServicelmpl.class);
    // 实例化md5加密工具类
    private CreateMD5Util md5 = CreateMD5Util.getInstance();
    //实例化文件上传工具类
    private UserFileUploadUtil uploadUtil = UserFileUploadUtil.getInstance();
    // 实例化字符验证工具类
    private UserFromFilterUtil charset = UserFromFilterUtil.getInstance();
    // 实例化结果集实体类,并定义为公共结果集
    private Result result = null;
    //实例化uuid工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();

    /**
     * 用户登陆验证方法
     *
     * @param user 用户登录信息
     * @return 登录结果
     */
    @Override
    public <T> T login(Users user) {
        boolean check = this.charset.checkLogin(user.getUserName(), user.getPassWord());
        if (check == false) {
            return (T) new Status("输入有误，请检查正确后重试", 5);
        }
        // 进行md5解密
        user.setPassWord(this.md5.getMd5(user.getPassWord()));
        UserInfo data = null;
        try {
            data = this.dao.login(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return (T) new Status("服务器走丢了！", 2);
        }
        if (data != null) {
            return (T) new Result("登录成功", 0, data);
        } else {
            return (T) new Status("用户名或密码错误", 1);
        }
    }

    /**
     * 用户信息注册方法
     *
     * @param user 用户注册信息，req http请求，files 用户文件
     * @return 注册结果
     */
    @Override
    @Transactional
    public Status register(Users user, HttpServletRequest req, MultipartFile[] files) {
        boolean check = this.charset.checkRegister(user);
        if (check == false) {
            return new Status("输入有误，请检查正确后重试", 5);
        }
        int var = this.checkUserName(user.getUserName());
        if (var == 1) {
            return new Status("用户名已被占用", 1);
        }
        int var1 = this.checkUserTel(user.getTelOrEmail());
        if (var1 == 1) {
            return new Status("手机号/邮箱已被占用", 1);
        }
        for (int i = 0, length = files.length; i < length; ++i) {
            if (files[i].isEmpty()) {
                return new Status("文件不能为空", 1);
            } else if (files[i].getSize() > 3000000) {
                return new Status("请上传小于3M大小的图片", 1);
            }
        }
        List list = null;
        try {
            list = this.uploadUtil.userFileUpload(files, req);
        } catch (Exception e) {
            e.printStackTrace();
            return new Status("图片上传失败，请重试", 1);
        }
        if (list == null) {
            return new Status("图片格式不正确", 1);
        }
        // 进行md5加密
        user.setPassWord(this.md5.getMd5(user.getPassWord()));
        // 分配用户编号
        user.setUid(this.uuid.getUUID().toString());
        user.setFileId(this.uuid.getUUID().toString());
        user.setDates(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //保存文件到数据库
        //实例化文件保存实体类
        FileData fileData = new FileData();
        fileData.setFileId(user.getFileId());
        try {
            this.filedao.saveFileDao(list, fileData);
            Grade grade = this.dao.queryRightsById(user.getLevel());
            int var2 = grade.getLevel();
            if (var2 == 4) {
                user.setLabel("法人");
            } else if (var2 == 3) {
                user.setLabel("授权代理人");
            } else {
                user.setLabel("自然人");
            }
            user.setRoleId(grade.getRoleId());
            this.dao.register(user);
            return new Status("注册成功", 0);
        } catch (Exception e) {
            e.printStackTrace();
            return new Status("注册失败，请重试！", 1);
        }
    }


    /**
     * 检查用户名是否重复方法
     *
     * @param userName 用户名
     * @return int
     */
    private int checkUserName(String userName) {
        List data = null;
        try {
            data = this.dao.checkUserName(userName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 1;
        }
        if (data.size() > 0)
            return 1;
        return 0;
    }

    //

    /**
     * 检查手机号/邮箱是否重复方法
     *
     * @param tel 联系方式
     * @return int
     */
    private int checkUserTel(String tel) {
        List data = null;
        try {
            data = this.dao.checkUserTel(tel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 1;
        }
        if (data.size() > 0)
            return 1;
        return 0;
    }


    /**
     * 功能：服务器启动初始化系统权限管理
     *
     * @return Boolean
     * @parameter 无
     */
    @Override
    @Transactional
    public boolean rightsInitialization() {
        List<Rights> rightsList = new ArrayList<Rights>();
        List<Grade> gradeList = new ArrayList<Grade>();
        Rights rights = new Rights();
        Grade grade = new Grade();
        rights.setRole("高级管理员");
        rights.setRoleId(this.uuid.getUUID().toString());
        rightsList.add(rights);
        grade.setRoleId(rights.getRoleId());
        grade.setLevel(5);
        grade.setLevelId(this.uuid.getUUID().toString());
        gradeList.add(grade);
        for (int i = 4; i > 2; --i) {
            Rights rights1 = new Rights();
            Grade grade1 = new Grade();
            rights1.setRole("管理员" + i);
            rights1.setRoleId(this.uuid.getUUID().toString());
            rightsList.add(rights1);
            grade1.setRoleId(rights1.getRoleId());
            grade1.setLevel(i);
            grade1.setLevelId(this.uuid.getUUID().toString());
            gradeList.add(grade1);
        }
        Rights rights2 = new Rights();
        Grade grade2 = new Grade();
        rights2.setRole("自然人");
        rights2.setRoleId(this.uuid.getUUID().toString());
        rightsList.add(rights2);
        grade2.setRoleId(rights2.getRoleId());
        grade2.setLevel(0);
        grade2.setLevelId(this.uuid.getUUID().toString());
        gradeList.add(grade2);
        try {
            this.dao.rightsInitialization(rightsList, gradeList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            this.logger.error(e);
            return false;
        }
    }

    /**
     * 功能：检查系统权限数据是否存在（防止服务器重启再次初始化权限数据）
     *
     * @return Boolean
     * @parameter 无
     */
    @Override
    @Transactional
    public boolean queryRights() {
        List<Rights> data = null;
        try {
            data = this.dao.queryRights();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            this.logger.error(e);
            return false;
        }
        if (data.size() > 0) {
            return false;
        } else {
            return true;
        }

    }
}
