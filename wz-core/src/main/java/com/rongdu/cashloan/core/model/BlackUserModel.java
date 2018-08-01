package com.rongdu.cashloan.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 黑名单用户信息详情
 * @author Administrator
 *
 */
public class BlackUserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Long id;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 身份证号码
	 */
	private String idNo;
	
	/**
	 * 借款本金
	 */
	private BigDecimal amount;
	
	/**
	 * 借款时间
	 */
	private String borrowTime;
	
	/**
	 * 还款时间
	 */
	private String repayTime;
	
	/**
	 * 逾期天数
	 */
	private Integer penaltyDay;
	
	/**
	 * 逾期罚息
	 */
	private BigDecimal penaltyAmout;
	
	/**
	 * 注册客户端
	 */
	private String registerClient;
	
	/**
	 * 渠道id
	 */
	private Long channelId;
	
	/**
	 * 拉黑原因
	 */
	private String blackReason;
	
	/**
	 * 逾期等级
	 */
	private String level;
	
	/**
	 * 渠道名字
	 */
	private String channelName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

	public Integer getPenaltyDay() {
		return penaltyDay;
	}

	public void setPenaltyDay(Integer penaltyDay) {
		this.penaltyDay = penaltyDay;
	}

	public BigDecimal getPenaltyAmout() {
		return penaltyAmout;
	}

	public void setPenaltyAmout(BigDecimal penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	public String getRegisterClient() {
		return registerClient;
	}

	public void setRegisterClient(String registerClient) {
		this.registerClient = registerClient;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getBlackReason() {
		return blackReason;
	}

	public void setBlackReason(String blackReason) {
		this.blackReason = blackReason;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
}
