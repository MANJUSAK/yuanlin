package com.goodsoft.yuanlin.controller.formfilter;

import com.goodsoft.yuanlin.entity.user.Users;

/**
 * function 登陆注册表单验证工具类
 * <p>
 * date 2017.05.25
 *
 * @author 严彬荣
 */
public class UserFromFilterUtil {

	/* 创建CharsetFilteringUtil单例 */

    private volatile static UserFromFilterUtil instance;

    private UserFromFilterUtil() {
    }

    public static UserFromFilterUtil getInstance() {

        if (instance == null) {
            synchronized (UserFromFilterUtil.class) {
                if (instance == null)
                    instance = new UserFromFilterUtil();
            }
        }
        return instance;
    }

    // 邮箱正则
    // private final String EMAIL =
    // "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    // 手机号正则
//    private final String TEL = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[^4,5-9]))\\d{8}$";
    // 身份证号码正则
    private final String IDCARD = "(^\\d{18}$)|(^\\d{15}$)";

    // 登陆字符验证
    public boolean checkLogin(String name, String pwd) {
        if (name == null || pwd == null) {
            return false;
        }
        if (name.length() <= 0) {
            return false;
        } else if (pwd.length() <= 3) {
            return false;
        } else {
            return true;
        }
    }

    // 注册字符验证
    public boolean checkRegister(Users user) {
        if (user.getUserName() == null
                || user.getIDcard() == null
                || user.getPassWord() == null || user.getTelOrEmail() == null) {
            return false;
        }
        if (user.getUserName().length() < 1 || user.getUserName().length() > 15) {
            return false;
        } else if (!(user.getIDcard().matches(this.IDCARD))) {
            return false;
        } else if (user.getPassWord().length() < 6
                || user.getPassWord().length() > 16) {
            return false;
        } else {
            return true;
        }
    }
}
