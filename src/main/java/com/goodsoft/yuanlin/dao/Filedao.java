package com.goodsoft.yuanlin.dao;

import com.goodsoft.yuanlin.entity.file.FileData;

import java.util.List;

/**
 * function 文件管理dao接口实现类
 * Created by 严彬荣 on 2017/8/1.
 */
public interface Filedao {
    //文件查询
    public List<FileData> qureyFileDao(String id);

    //文件保存
    public void saveFileDao(List<String> msg, FileData var) throws Exception;
}
