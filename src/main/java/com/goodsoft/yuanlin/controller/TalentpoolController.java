package com.goodsoft.yuanlin.controller;

import com.goodsoft.yuanlin.entity.talentpool.Corporation;
import com.goodsoft.yuanlin.entity.talentpool.Employees;
import com.goodsoft.yuanlin.entity.talentpool.Principal;
import com.goodsoft.yuanlin.service.TalentpoolService;
import com.goodsoft.yuanlin.util.resultentity.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * function 人才库访问数据入口java类
 * <p>
 * date 2017.05.18
 *
 * @author 严彬荣
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/talentpool")
public class TalentpoolController {
    @Resource
    private TalentpoolService service;
    // 实例化公共结果集
    @SuppressWarnings("unused")
    private Status result = null;

    /**
     * 查询法人库信息数据访问接口
     *
     * @return 查询结果
     * @Parma request http请求
     */
    @ResponseBody
    @RequestMapping(value = "/queryCorporation", produces = "application/json;charset=utf-8")
    public Object queryCorporationController(HttpServletRequest request) {
        return this.service.queryCorporationService(request);
    }

    /**
     * 查询从业人员库信息数据访问接口
     *
     * @return 查询结果
     * @Parma request http请求
     */
    @ResponseBody
    @RequestMapping(value = "/queryEmployees", produces = "application/json;charset=utf-8")
    public Object queryEmployeesController(HttpServletRequest request) {
        return this.service.queryEmployeesService(request);
    }

    /**
     * 查询企业负责人信息数据访问接口
     *
     * @return 查询结果
     * @Parma request http请求
     */
    @ResponseBody
    @RequestMapping(value = "/queryPrincipal", produces = "application/json;charset=utf-8")
    public Object queryPrincipalController(HttpServletRequest request) {
        return this.service.queryPrincipalService(request);
    }

    /**
     * 法人库数据录入访问接口
     *
     * @return 添加结果
     * @Parma request http请求，files 法人库文件，file 企业负责人文件，msg 表单提交数据实体
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST})
    @ResponseBody
    @RequestMapping(value = "/saveCorporation", produces = "application/json;charset=utf-8", method = {RequestMethod.POST})
    public Status saveCorporationController(HttpServletRequest request, @RequestParam("cfiles") MultipartFile[] cfiles, @RequestParam("pfiles") MultipartFile[] pfiles, Corporation msg, Principal msg1) {
        this.result = this.service.saveCorporationService(cfiles, request, msg);
        if (this.result.getCode() == 0) {
            this.result = this.service.savePrincipalService(pfiles, request, msg1);
        }
        return this.result;
    }

    /**
     * 从业人员库数据录入访问接口
     *
     * @return 添加结果
     * @Parma request http请求，files 上传的文件，msg 表单提交数据实体
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST})
    @ResponseBody
    @RequestMapping(value = "/saveEmployees", produces = "application/json;charset=utf-8", method = {RequestMethod.POST})
    public Status saveEmployeesController(HttpServletRequest request, @RequestParam("files") MultipartFile[] files, Employees msg) {
        return this.service.saveEmployeesService(files, request, msg);
    }

    /**
     * 企业负责人数据录入访问接口
     *
     * @return 添加结果
     * @Parma request http请求，files 上传的文件，msg 表单提交数据实体
     */
    @CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST})
    @ResponseBody
    @RequestMapping(value = "/savePrincipal", produces = "application/json;charset=utf-8", method = {RequestMethod.POST})
    public Status savePrincipalController(HttpServletRequest request, @RequestParam("files") MultipartFile[] files, Principal msg) {
        return this.service.savePrincipalService(files, request, msg);
    }
}
