package com.rongdu.cashloan.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OverdueCollectionModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 催收订单的id
	 */
	private Long id;
	
	/**
	 * 还款计划的id
	 */
	private Long repayId;

	/**
	 * 客户姓名
	 */
	private String personalName;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	/**
	 * 手机号码
	 */
	private String mobileNo;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 合同编号
	 */
	private String contractNumber;
	
	/**
	 * 贷款日期
	 */
	private Date loanDate;
	
	/**
	 * 还款期数
	 */
	private Integer periods;
	
	/**
	 * 合同金额(元)
	 */
	private Double contractAmount;
	
	/**
	 * 逾期总金额(元)
	 */
	private Double overdueAmount;
	
	/**
	 * 逾期本金(元)
	 */
	private Double overdueCapital;
	
	/**
	 * 逾期利息(元)
	 */
	private Double overDueInterest;
	
	/**
	 * 逾期罚息(元)
	 */
	private Double overdueFine;
	
	/**
	 * 逾期滞纳金(元)
	 */
	private Double overdueDelayFine;
	
	/**
	 * 逾期日期
	 */
	private Date overDueDate;
	
	/**
	 * 逾期天数
	 */
	private Integer overDueDays;
	
	/**
	 * 服务费
	 */
	private Double serveFee;
	
	/**
	 * 客户还款卡银行
	 */
	private String depositBank;
	
	/**
	 * 客户还款卡号
	 */
	private String cardNumber;
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 身份证户籍地址
	 */
	private String idCardAddress;
	
	/**
	 * 工作单位名称
	 */
	private String companyName;
	
	/**
	 * 工作单位地址
	 */
	private String companyAddr;
	
	/**
	 * 工作单位电话
	 */
	private String companyPhone;
	
	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 逾期管理费(元)
	 */
	private Double overdueManageFee;
	
	/**
	 * 仲裁状态
	 */
	private String processState;
	
	private List<OverdueCollectionUserModel> contactList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRepayId() {
		return repayId;
	}

	public void setRepayId(Long repayId) {
		this.repayId = repayId;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(Double overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Double getOverdueCapital() {
		return overdueCapital;
	}

	public void setOverdueCapital(Double overdueCapital) {
		this.overdueCapital = overdueCapital;
	}

	public Double getOverDueInterest() {
		return overDueInterest;
	}

	public void setOverDueInterest(Double overDueInterest) {
		this.overDueInterest = overDueInterest;
	}

	public Double getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(Double overdueFine) {
		this.overdueFine = overdueFine;
	}

	public Double getOverdueDelayFine() {
		return overdueDelayFine;
	}

	public void setOverdueDelayFine(Double overdueDelayFine) {
		this.overdueDelayFine = overdueDelayFine;
	}

	public Date getOverDueDate() {
		return overDueDate;
	}

	public void setOverDueDate(Date overDueDate) {
		this.overDueDate = overDueDate;
	}

	public Integer getOverDueDays() {
		return overDueDays;
	}

	public void setOverDueDays(Integer overDueDays) {
		this.overDueDays = overDueDays;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIdCardAddress() {
		return idCardAddress;
	}

	public void setIdCardAddress(String idCardAddress) {
		this.idCardAddress = idCardAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Double getOverdueManageFee() {
		return overdueManageFee;
	}

	public void setOverdueManageFee(Double overdueManageFee) {
		this.overdueManageFee = overdueManageFee;
	}

	public List<OverdueCollectionUserModel> getContactList() {
		return contactList;
	}

	public void setContactList(List<OverdueCollectionUserModel> contactList) {
		this.contactList = contactList;
	}

	public Double getServeFee() {
		return serveFee;
	}

	public void setServeFee(Double serveFee) {
		this.serveFee = serveFee;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

}
