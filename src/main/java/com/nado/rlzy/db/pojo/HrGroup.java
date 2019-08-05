package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author lushuaiyu
 */
@Data
@Table(name = "hr_group")
public class HrGroup {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "GroupName")
    private String groupname;

    @Transient
    private String recruitedCompany;

    @Transient
    private String certifier;

    @Column(name = "GroupInfo")
    private String groupinfo;

    @Column(name = "legalPerson")
    private String legalperson;

    @Column(name = "socialCreditCode")
    private String socialcreditcode;

    @Column(name = "registrationPlace")
    private String registrationplace;

    @Column(name = "GroupAddress")
    private String groupaddress;

    @Column(name = "IdentifyTime")
    private Date identifytime;

    @Column(name = "CertificationUnitId")
    private Integer certificationunitid;

    @Column(name = "BusinessLicense")
    private String businesslicense;

    @Column(name = "HelpProve")
    private String helpprove;

    @Column(name = "remark")
    private String remark;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "GroupTypeId")
    private Integer grouptypeid;

    @Column(name = "CertifierId")
    private Integer certifierid;

    @Column(name = "foulNum")
    private Integer foulnum;

    @Column(name = "CreateTime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;

    @Column(name = "DeleteFlag")
    private Byte deleteflag;

    @Column(name = "Pid")
    private Integer pid;

    @Transient
    private String interviewAddress;

    @Column(name = "person_num")
    private String personNum;

    @Column(name = "co_policy  ")
    private String coPolicy;

    /**
     * 代招单位和简章是一对多
     */
    @Transient
    private List<HrBriefchapter> hriefchapter;

    @ApiModelProperty(value = "企业许可证")
    @Column(name = "enterprise_license")
    private String enterpriseLicense;

    @ApiModelProperty(value = "注册人委托证明")
    @Column(name = "registrant_certificate")
    private String registrantCertificate;

    @Column(name = "type")
    private Integer type;

    @Column(name = "groupStatus")
    private Integer groupStatus;


}