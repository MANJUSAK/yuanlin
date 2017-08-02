package com.goodsoft.yuanlin.entity.user;

/**
 * function 用户登录返回信息
 * Created by 严彬荣 on 2017/8/2.
 */
public class UserInfo {
    // 用户ID
    private String uid;
    //权限id
    private String roleId;
    // 联系电话/邮箱
    private String telOrEmail;
    // 用户名
    private String userName;
    // 身份证号
    private String IDcard;
    // 注册日期
    private String dates;
    //用户标签
    private String label;

    public UserInfo() {
    }

    public UserInfo(String uid, String roleId, String telOrEmail, String userName, String IDcard, String dates, String label) {
        this.uid = uid;
        this.roleId = roleId;
        this.telOrEmail = telOrEmail;
        this.userName = userName;
        this.IDcard = IDcard;
        this.dates = dates;
        this.label = label;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getTelOrEmail() {
        return telOrEmail;
    }

    public void setTelOrEmail(String telOrEmail) {
        this.telOrEmail = telOrEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
