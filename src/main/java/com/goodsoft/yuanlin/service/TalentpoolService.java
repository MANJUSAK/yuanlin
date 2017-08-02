package com.goodsoft.yuanlin.service;

import com.goodsoft.yuanlin.entity.talentpool.Corporation;
import com.goodsoft.yuanlin.entity.talentpool.Employees;
import com.goodsoft.yuanlin.entity.talentpool.Principal;
import com.goodsoft.yuanlin.util.resultentity.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface TalentpoolService {

    // 查询法人库信息数据接口
    public <T> T queryCorporationService(HttpServletRequest request);

    // 查询从业人才库信息数据接口
    public <T> T queryEmployeesService(HttpServletRequest request);

    // 查询企业负责人信息数据接口
    public <T> T queryPrincipalService(HttpServletRequest request);

    // 法人库数据录入接口
    public Status saveCorporationService(MultipartFile[] files, HttpServletRequest request, Corporation msg);

    // 从业人才库数据录入接口
    public Status saveEmployeesService(MultipartFile[] files, HttpServletRequest request, Employees msg);

    // 企业负责人数据录入接口
    public Status savePrincipalService(MultipartFile[] files, HttpServletRequest request, Principal msg);
}
