package com.goodsoft.yuanlin.entity.talentpool;

import com.goodsoft.yuanlin.entity.file.FileData;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Corporation entity. 法人库表实体
 * <p>
 * <p>
 * date 2017-05-18
 *
 * @author 严彬荣
 */
@Entity
@Table(name = "gs_corporation", catalog = "yuanlin")
public class Corporation implements java.io.Serializable {

    private static final long serialVersionUID = -5296183546718289864L;
    // 表id
    private Integer id;
    // 企业名称
    private String companyName;
    // 企业简介
    private String companyProfile;
    // 注册地址
    private String registeredAddress;
    // 详细地址
    private String detailAddress;
    // 注册资金
    private BigDecimal registeredCapital;
    //营业执照编号
    private String registeredId;
    //资质证书编号
    private String qualificationId;
    // 录入时间
    private String dates;
    // 文件编号
    private String fileId;
    // 企业营业执照文件路径
    private List<FileData> url;

    public Corporation() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Corporation(Integer id, String companyName, String companyProfile, String registeredAddress, String detailAddress, BigDecimal registeredCapital, String registeredId, String qualificationId, String dates, String fileId) {
        this.id = id;
        this.companyName = companyName;
        this.companyProfile = companyProfile;
        this.registeredAddress = registeredAddress;
        this.detailAddress = detailAddress;
        this.registeredCapital = registeredCapital;
        this.registeredId = registeredId;
        this.qualificationId = qualificationId;
        this.dates = dates;
        this.fileId = fileId;
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

    @Column(name = "companyName", nullable = false, length = 100)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "companyProfile", length = 3000)
    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
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

    @Column(name = "registeredAddress", nullable = false, length = 300)
    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    @Column(name = "detailAddress", nullable = false, length = 300)
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Column(name = "registeredCapital", nullable = false)
    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    @Column(name = "registeredId", nullable = false, length = 50)
    public String getRegisteredId() {
        return registeredId;
    }

    public void setRegisteredId(String registeredId) {
        this.registeredId = registeredId;
    }

    @Column(name = "qualificationId", nullable = false, length = 50)
    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    @Transient
    public List<FileData> getUrl() {
        return url;
    }

    public void setUrl(List<FileData> url) {
        this.url = url;
    }

}
