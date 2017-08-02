package com.goodsoft.yuanlin.entity.talentpool;

import com.goodsoft.yuanlin.entity.file.FileData;

import javax.persistence.*;
import java.util.List;

/**
 * Corporation entity. 企业负责人表实体
 * <p>
 * <p>
 * date 2017-05-18
 *
 * @author 严彬荣
 */
@Entity
@Table(name = "gs_principal", catalog = "yuanlin")
public class Principal implements java.io.Serializable {
    private Integer id;//表id
    private String name;//姓名
    private int gender;//性别（1为男，2为女）
    private String tel;//手机号
    private String fileId;//文件编号
    private String types;//负责人类型
    private String dates;//录入时间
    private String registeredIds; //营业执照编号（关联法人库信息表字段）
    private List<FileData> url;

    public Principal() {
    }

    public Principal(Integer id, String name, int gender, String tel, String fileId, String types) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.fileId = fileId;
        this.types = types;
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

    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "gender", nullable = false)
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Column(name = "tel", nullable = false, length = 15)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "fileId", nullable = false, length = 32)
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Column(name = "types", nullable = false, length = 20)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Column(name = "dates", nullable = false, length = 30)
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    @Column(name = "registeredId", nullable = false, length = 50)
    public String getRegisteredIds() {
        return registeredIds;
    }

    public void setRegisteredIds(String registeredIds) {
        this.registeredIds = registeredIds;
    }

    @Transient
    public List<FileData> getUrl() {
        return url;
    }

    public void setUrl(List<FileData> url) {
        this.url = url;
    }
}
