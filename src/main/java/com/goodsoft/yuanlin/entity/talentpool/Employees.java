package com.goodsoft.yuanlin.entity.talentpool;

import com.goodsoft.yuanlin.entity.file.FileData;

import javax.persistence.*;
import java.util.List;

/**
 * employees entity. 从业人员库表实体
 * <p>
 * <p>
 * date 2017-05-18
 *
 * @author 严彬荣
 */
@Entity
@Table(name = "gs_employees", catalog = "yuanlin")
public class Employees implements java.io.Serializable {

    private static final long serialVersionUID = -7439095567805595607L;
    // 表id
    private Integer id;
    // 姓名
    private String name;
    // 性别
    private Integer gender;
    //手机号
    private String tel;
    // 身份证号
    private String IDcard;
    // 产生关系时间
    private String dates;
    //文件编号
    private String fileId;
    //是否继续教育
    private String education;
    //从业经历
    private String experience;

    // 资质证书文件路径
    private List<FileData> url;

    public Employees() {
        super();
    }

    public Employees(Integer id, String name, Integer gender, String tel, String IDcard, String dates, String fileId, String education, String experience) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.IDcard = IDcard;
        this.dates = dates;
        this.fileId = fileId;
        this.education = education;
        this.experience = experience;
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

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "gender", nullable = false, length = 1)
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column(name = "dates", nullable = false, length = 20)
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    @Column(name = "fileId", nullable = false, length = 32)
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Column(name = "tel", nullable = false, length = 15)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "education", nullable = false, length = 1)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name = "experience", length = 300)
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Column(name = "IDcard", nullable = false, length = 20)
    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    @Transient
    public List<FileData> getUrl() {
        return url;
    }

    public void setUrl(List<FileData> url) {
        this.url = url;
    }

}
