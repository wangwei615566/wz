package com.czwx.cashloan.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProfitAmount {
    private Long id;

    private Long userId;

    private BigDecimal total;

    private BigDecimal canCashed;

    private BigDecimal cashed;

    private BigDecimal frozen;

    private Byte state;

    private Date updateTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCanCashed() {
        return canCashed;
    }

    public void setCanCashed(BigDecimal canCashed) {
        this.canCashed = canCashed;
    }

    public BigDecimal getCashed() {
        return cashed;
    }

    public void setCashed(BigDecimal cashed) {
        this.cashed = cashed;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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