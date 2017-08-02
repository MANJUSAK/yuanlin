package com.goodsoft.yuanlin.util.sqlentity;

/**
 * function 数据库操作合同管理sql语句辅助类
 *
 * @author 严彬荣
 */
public class FileSql {


    // 人才库文件数据保存sql
    private String saveTalentpoolSql = "insert into gs_talentpoolFile (path,fileId,isNo) values (?,?,?)";

    public String getSaveTalentpoolSql() {
        return saveTalentpoolSql;
    }

    public void setSaveTalentpoolSql(String saveTalentpoolSql) {
        this.saveTalentpoolSql = saveTalentpoolSql;
    }

}
