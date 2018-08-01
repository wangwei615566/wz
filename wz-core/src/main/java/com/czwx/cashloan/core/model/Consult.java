package com.czwx.cashloan.core.model;

import java.util.Date;

public class Consult {
    private Long id;

    private String consultImg;

    private String consultUrl;

    private String consultDetail;

    private String state;

    private String type;

    private String topState;

    private Date createTime;

    private Date updateTime;

    public String getTopState() {
        return topState;
    }

    public void setTopState(String topState) {
        this.topState = topState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsultImg() {
        return consultImg;
    }

    public void setConsultImg(String consultImg) {
        this.consultImg = consultImg == null ? null : consultImg.trim();
    }

    public String getConsultUrl() {
        return consultUrl;
    }

    public void setConsultUrl(String consultUrl) {
        this.consultUrl = consultUrl == null ? null : consultUrl.trim();
    }

    public String getConsultDetail() {
        return consultDetail;
    }

    public void setConsultDetail(String consultDetail) {
        this.consultDetail = consultDetail == null ? null : consultDetail.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}