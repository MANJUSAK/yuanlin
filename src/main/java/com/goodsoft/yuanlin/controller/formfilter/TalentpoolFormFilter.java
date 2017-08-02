package com.goodsoft.yuanlin.controller.formfilter;

import com.goodsoft.yuanlin.entity.talentpool.Corporation;
import com.goodsoft.yuanlin.entity.talentpool.Employees;

/**
 * function 人才库表单验证工具类
 * <p>
 * date 2017.05.25
 *
 * @author 严彬荣
 */
@SuppressWarnings("ALL")
public class TalentpoolFormFilter {
    /* 创建本类为单例模式 */
    private volatile static TalentpoolFormFilter instance;

    private TalentpoolFormFilter() {
    }

    public static TalentpoolFormFilter getInstance() {
        if (instance == null) {
            synchronized (TalentpoolFormFilter.class) {
                if (instance == null)
                    instance = new TalentpoolFormFilter();
            }
        }
        return instance;
    }

    // 手机号正则
    // private final String TEL =
    // "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    // 身份证号码正则
    // private final String IDCARD = "(^\\d{18}$)|(^\\d{15}$)";

    // 法人库表单验证
    public boolean corporationForm(Corporation msg) {
        if (msg.getCompanyName() == null
                || msg.getCompanyProfile() == null) {
            return false;
        }
        if (msg.getCompanyName().length() <= 0
                || msg.getCompanyName().length() > 100) {
            return false;
        } else if (msg.getCompanyProfile().length() <= 0
                || msg.getCompanyProfile().length() > 3000) {
            return false;
        } else {
            return true;
        }
    }

    // 人才库表单验证
    public boolean employeesForm(Employees msg) {
        if (msg.getName() == null || msg.getGender() == null) {
            return false;
        }
        if (msg.getName().length() <= 0 || msg.getName().length() > 50) {
            return false;
        } else if (msg.getGender().longValue() < 1
                || msg.getGender().longValue() > 2) {
            return false;
        } else {
            return true;
        }
    }
}
