package com.nado.rlzy.db.pojo;

import java.util.Date;

public class HrAcct {
    private Integer id;

    private Integer userid;

    private Long acctbalance;

    private Long icebalance;

    private Byte deleteflag;

    private Date modifytime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getAcctbalance() {
        return acctbalance;
    }

    public void setAcctbalance(Long acctbalance) {
        this.acctbalance = acctbalance;
    }

    public Long getIcebalance() {
        return icebalance;
    }

    public void setIcebalance(Long icebalance) {
        this.icebalance = icebalance;
    }

    public Byte getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Byte deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}