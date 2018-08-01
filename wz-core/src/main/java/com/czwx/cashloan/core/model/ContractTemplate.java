package com.czwx.cashloan.core.model;

import java.io.Serializable;
import java.util.Date;

public class ContractTemplate implements Serializable {
    private Long id;

    private String templateId;

    private String templateName;

    private String state;

    private String type;

    private String loanProduceCode;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoanProduceCode() {
        return loanProduceCode;
    }

    public void setLoanProduceCode(String loanProduceCode) {
        this.loanProduceCode = loanProduceCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}