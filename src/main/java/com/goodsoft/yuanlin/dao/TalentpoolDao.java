package com.goodsoft.yuanlin.dao;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface TalentpoolDao {

    // 查询人才库信息数据dao接口
    public List queryTalentpoolDao(Class c);

    // 查询人才库信息数据dao接口
    public List queryCorporationDaoById(String id);

    // 人才库信息数据录入dao接口
    public void saveDao(Object msg) throws Exception;

    // 人才库信息多数据录入dao接口
    public void saveDao(List<T> msg) throws Exception;


}
