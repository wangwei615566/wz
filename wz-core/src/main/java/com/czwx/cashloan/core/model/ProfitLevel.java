package com.czwx.cashloan.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProfitLevel {
    private Long id;

    private String name;

    private Byte level;

    private BigDecimal rate;

    private BigDecimal secondRate;

    private BigDecimal regAmount;

    private String remark;

    private Date updateTime;

    private Date createTime;
    /**
     * 是否会员
     */
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getSecondRate() {
        return secondRate;
    }

    public void setSecondRate(BigDecimal secondRate) {
        this.secondRate = secondRate;
    }

    public BigDecimal getRegAmount() {
        return regAmount;
    }

    public void setRegAmount(BigDecimal regAmount) {
        this.regAmount = regAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}