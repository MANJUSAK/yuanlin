package com.goodsoft.yuanlin.entity.file;

import javax.persistence.*;

/**
 * FileData entity.文件保存路径信息表实体
 *
 * @author 严彬荣
 */
@Entity
@Table(name = "gs_fileData", catalog = "yuanlin")
public class FileData implements java.io.Serializable {

    private static final long serialVersionUID = -4528651329306759346L;
    // 表ID
    private Integer tid;
    // 路径
    private String path;
    // 图片路径编号
    private String fileId;

    /**
     * default constructor
     */
    public FileData() {
    }

    public FileData(Integer tid, String path, String fileId) {
        this.tid = tid;
        this.path = path;
        this.fileId = fileId;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid", nullable = false)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Column(name = "path", length = 150)
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "fileId", length = 32)
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

}
