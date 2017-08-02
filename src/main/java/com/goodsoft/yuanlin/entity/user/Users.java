package com.goodsoft.yuanlin.entity.user;

import javax.persistence.*;

/**
 * Users entity.用户表实体
 *
 * @author 严彬荣
 */
@Entity
@Table(name = "users", catalog = "yuanlin")
public class Users implements java.io.Serializable {

    private static final long serialVersionUID = -7630658413532401374L;
    // 表ID
    private Integer id;
    // 用户ID
    private String uid;
    //权限id
    private String roleId;
    // 密码
    private String passWord;
    // 联系电话/邮箱
    private String telOrEmail;
    // 用户名
    private String userName;
    // 身份证号
    private String IDcard;
    // 文件路径编号
    private String fileId;
    // 注册日期
    private String dates;
    //用户标签
    private String label;
    //初始用户权限等级
    private int level;
    // 数据状态参数
    private int isNo;

    // Constructors

    /**
     * default constructor
     */
    public Users() {
        this.isNo = 0;
    }

    public Users(String uid, String roleId, String telOrEmail, String userName, String IDcard, String dates, String label) {
        this.uid = uid;
        this.roleId = roleId;
        this.telOrEmail = telOrEmail;
        this.userName = userName;
        this.IDcard = IDcard;
        this.dates = dates;
        this.label = label;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "uid", nullable = false, unique = true, length = 32)
    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Column(name = "userName", nullable = false, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "passWord", nullable = false, length = 20)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    @Column(name = "telOrEmail", nullable = false, length = 20)
    public String getTelOrEmail() {
        return telOrEmail;
    }

    public void setTelOrEmail(String telOrEmail) {
        this.telOrEmail = telOrEmail;
    }


    @Column(name = "dates", length = 50, nullable = false)
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    @Column(name = "IDcard", length = 20)
    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }


    @Column(name = "fileId", length = 32, unique = true, nullable = false)
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Column(name = "roleId", length = 32, nullable = false)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "isNo", nullable = false)
    public int getIsNo() {
        return isNo;
    }

    public void setIsNo(int isNo) {
        this.isNo = isNo;
    }


    @Column(name = "label", length = 50, nullable = false)
    public String
    getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Transient
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}