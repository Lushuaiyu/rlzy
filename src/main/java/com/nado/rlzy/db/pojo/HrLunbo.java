package com.nado.rlzy.db.pojo;

import javax.persistence.*;

@Table(name = "hr_lunbo")
public class HrLunbo {
    /**
     * 企业轮播图列表
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 企业Id
     */
    @Column(name = "groupId")
    private Integer groupid;

    private String title;

    private String url;

    /**
     * 状态（0禁用、1启用）
     */
    private Integer status;

    /**
     * 获取企业轮播图列表
     *
     * @return id - 企业轮播图列表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置企业轮播图列表
     *
     * @param id 企业轮播图列表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取企业Id
     *
     * @return groupId - 企业Id
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * 设置企业Id
     *
     * @param groupid 企业Id
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取状态（0禁用、1启用）
     *
     * @return status - 状态（0禁用、1启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（0禁用、1启用）
     *
     * @param status 状态（0禁用、1启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}