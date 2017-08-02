package com.goodsoft.yuanlin.service.servicelmpl;

import com.goodsoft.yuanlin.controller.formfilter.TalentpoolFormFilter;
import com.goodsoft.yuanlin.dao.Filedao;
import com.goodsoft.yuanlin.dao.TalentpoolDao;
import com.goodsoft.yuanlin.entity.file.FileData;
import com.goodsoft.yuanlin.entity.talentpool.Corporation;
import com.goodsoft.yuanlin.entity.talentpool.Employees;
import com.goodsoft.yuanlin.entity.talentpool.Principal;
import com.goodsoft.yuanlin.service.TalentpoolService;
import com.goodsoft.yuanlin.util.DomainNameUtil;
import com.goodsoft.yuanlin.util.TalentpoolFileUpload;
import com.goodsoft.yuanlin.util.UUIDUtil;
import com.goodsoft.yuanlin.util.resultentity.Result;
import com.goodsoft.yuanlin.util.resultentity.Status;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * function 人才库模块业务逻辑Java类
 * <p>
 * <p>
 * date 2017-05-18
 *
 * @author 严彬荣
 */
@SuppressWarnings("ALL")
@Service
public class TalentpoolServicelmpl implements TalentpoolService {

    @Resource
    private TalentpoolDao dao;
    @Resource
    private Filedao filedao;
    // 实例化文件保存工具类
    private TalentpoolFileUpload fileUpload = TalentpoolFileUpload.getInstance();
    // 实例化文件保存实体类
    private FileData fileData = new FileData();
    // 实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();
    // 实例化公共集合
    private List<String> list = null;
    // 实例化服务器域名工具类
    private DomainNameUtil http = DomainNameUtil.getInstance();
    // 实例化公共结果集
    private Result result = null;
    // 实例化文件路径集合
    private List<FileData> url = null;
    // 实例化表单验证工具类
    private TalentpoolFormFilter charset = TalentpoolFormFilter.getInstance();

    /**
     * 查询法人库信息数据方法
     *
     * @param request http请求
     * @return 查询结果
     */
    @Override
    public <T> T queryCorporationService(HttpServletRequest request) {
        List<Corporation> list = null;
        try {
            list = this.dao.queryTalentpoolDao(Corporation.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return (T) new Status("系统繁忙，请稍后重试！", 2);
        }
        if (list.size() > 0) {
            try {
                for (int i = 0, length = list.size(); i < length; i++) {
                    this.url = this.filedao.qureyFileDao(list.get(i).getFileId());
                    if (this.url.size() > 0) {
                        list.get(i).setUrl(this.url);
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.result = new Result(0, list);
            this.result.setPath(this.http.getDomainName(request));
            return (T) this.result;
        } else {
            return (T) new Status("没有相关数据", 1);
        }
    }


    /**
     * 查询从业人库信息数据方法
     *
     * @param request http请求
     * @return 查询结果
     */
    @Override
    public <T> T queryEmployeesService(HttpServletRequest request) {
        List<Employees> list = null;
        try {
            list = this.dao.queryTalentpoolDao(Employees.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return (T) new Status("系统繁忙，请稍后重试！", 2);
        }
        if (list.size() > 0) {
            try {
                for (int i = 0, length = list.size(); i < length; i++) {
                    this.url = this.filedao.qureyFileDao(list.get(i).getFileId());
                    if (this.url.size() > 0) {
                        list.get(i).setUrl(this.url);
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.result = new Result(0, list);
            this.result.setPath(this.http.getDomainName(request));
            return (T) this.result;
        } else {
            return (T) new Status("没有相关数据", 1);
        }
    }

    @Override
    public <T> T queryPrincipalService(HttpServletRequest request) {
        List<Principal> list = null;
        try {
            list = this.dao.queryTalentpoolDao(Principal.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return (T) new Status("系统繁忙，请稍后重试！", 2);
        }
        if (list.size() > 0) {
            try {
                for (int i = 0, length = list.size(); i < length; i++) {
                    this.url = this.filedao.qureyFileDao(list.get(i).getFileId());
                    if (this.url.size() > 0) {
                        list.get(i).setUrl(this.url);
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.result = new Result(0, list);
            this.result.setPath(this.http.getDomainName(request));
            return (T) this.result;
        } else {
            return (T) new Status("没有相关数据", 1);
        }
    }


    /**
     * 法人库信息数据录入方法
     *
     * @param request http请求，files 上传的文件，msg 录入的数据
     * @return 录入结果
     */
    @Override
    public Status saveCorporationService(MultipartFile[] files, HttpServletRequest request, Corporation msg) {
        boolean check = this.charset.corporationForm(msg);
        if (check == false) {
            return new Status("输入有误，请检查正确后重试", 5);
        }
        List list = this.dao.queryCorporationDaoById(msg.getRegisteredId());
        if (list.size() > 0) {
            return new Status(0, "已存在该条数据");
        }
        for (int i = 0, length = files.length; i < length; ++i) {
            if (files[i].isEmpty()) {
                return new Status("文件不能为空", 1);
            } else if (files[i].getSize() > 10000000) {
                return new Status("请上传小于10M大小的图片", 1);
            }
        }
        try {
            this.list = this.fileUpload.talentpoolFileUpload(files, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Status("文件上传失败，请重试", 2);

        }
        if (this.list == null) {
            return new Status("图片格式不正确", 2);
        }
        msg.setDates(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        msg.setFileId(this.uuid.getUUID().toString());
        this.fileData.setFileId(msg.getFileId());
        try {
            this.filedao.saveFileDao(this.list, this.fileData);
            this.dao.saveDao(msg);
            return new Status("信息录入成功", 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Status("系统繁忙，请稍后重试", 2);

        }
    }

    /**
     * 从业人员库信息数据录入方法
     *
     * @param request http请求，files 上传的文件，msg 录入的数据
     * @return 录入结果
     */
    @Override
    public Status saveEmployeesService(MultipartFile[] files, HttpServletRequest request, Employees msg) {
        boolean check = this.charset.employeesForm(msg);
        if (check == false) {
            return new Status("输入有误，请检查正确后重试", 5);
        }
        for (int i = 0, length = files.length; i < length; ++i) {
            if (files[i].getSize() > 10000000) {
                return new Status("请上传小于10M大小的图片", 1);
            }
        }
        try {
            this.list = this.fileUpload.talentpoolFileUpload(files, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Status("文件上传失败，请重试", 2);
        }
        if (this.list == null) {
            return new Status("文件格式不正确", 2);
        }
        msg.setDates(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        msg.setEid(this.uuid.getUUID().toString());
        msg.setFileId(this.uuid.getUUID().toString());
        this.fileData.setFileId(msg.getFileId());
        try {
            this.filedao.saveFileDao(this.list, this.fileData);
            this.dao.saveDao(msg);
            return new Status("信息录入成功", 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Status("系统繁忙，请稍后重试", 2);
        }
    }

    /**
     * 企业负责人信息数据录入方法
     *
     * @param request http请求，files 上传的文件，msg 录入的数据
     * @return 录入结果
     */
    @Override
    public Status savePrincipalService(MultipartFile[] files, HttpServletRequest request, Principal msg) {
        for (int i = 0, length = files.length; i < length; ++i) {
            if (files[i].isEmpty()) {
                return new Status("文件不能为空", 1);
            } else if (files[i].getSize() > 3000000) {
                return new Status("请上传小于3M大小的图片", 1);
            }
        }
        try {
            this.list = this.fileUpload.talentpoolFileUpload(files, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Status("文件上传失败，请重试", 2);
        }
        if (this.list == null) {
            return new Status("文件格式不正确", 2);
        }
        msg.setDates(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        msg.setFileId(this.uuid.getUUID().toString());
        this.fileData.setFileId(msg.getFileId());
        try {
            this.filedao.saveFileDao(this.list, this.fileData);
            this.dao.saveDao(msg);
            return new Status("信息录入成功", 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Status("系统繁忙，请稍后重试", 2);
        }
    }
}
