package com.wz.cashloan.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserCashLog {
    private Long id;

    private Long userId;

    private Byte cashWay;

    private String accountNo;

    private String accountName;

    private BigDecimal amount;

    private BigDecimal fee;
    
    private Long inviteId;

    private Byte state;

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

    public Byte getCashWay() {
        return cashWay;
    }

    public void setCashWay(Byte cashWay) {
        this.cashWay = cashWay;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserCashLog() {

    }

    public UserCashLog(Long userId, Byte cashWay, String accountNo, String accountName, BigDecimal amount, BigDecimal fee, Date createTime) {
        this.userId = userId;
        this.cashWay = cashWay;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.amount = amount;
        this.fee = fee;
        this.createTime = createTime;
    }

    public Long getInviteId() {
		return inviteId;
	}

	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}

	public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}