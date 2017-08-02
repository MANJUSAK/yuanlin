package com.goodsoft.yuanlin.util.sqlentity;

/**
 * function 数据库操作项目管理sql语句辅助类
 *
 * @author 严彬荣
 */
public class SQL {

    // 用户文件信息保存sql
    private String saveUserFileSql = "insert into gs_userFile(path,fileId) values (?,?)";

    public String getSaveUserFileSql() {
        return saveUserFileSql;
    }

    public void setSaveUserFileSql(String saveUserFileSql) {
        this.saveUserFileSql = saveUserFileSql;
    }
}
