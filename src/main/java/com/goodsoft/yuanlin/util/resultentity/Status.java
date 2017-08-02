package com.goodsoft.yuanlin.util.resultentity;

/**
 * 结果集实体
 *
 * @author 严彬荣
 */
public class Status implements java.io.Serializable {
    private int code;
    private String explain;

    public Status() {
    }

    public Status(String explain, int code) {
        this.explain = explain;
        this.code = code;
    }

    public Status(int code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
