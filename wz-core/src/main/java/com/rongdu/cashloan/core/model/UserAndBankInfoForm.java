package com.rongdu.cashloan.core.model;

import java.io.Serializable;

/**
 * 个人及银行卡信息的实体
 * @author Yang.Hu
 *
 */
public class UserAndBankInfoForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4321196016215227688L;
	private String realName;	//真实姓名
	private String cardNo;	//身份证号码
	private String mobile;	//手机号码
	private String bankCode; 	//所属银行编号
	private String bankCardNo;	//银行卡号
	private String bankProvince;	//银行卡所属省份编码
	private String bankCity;	//银行卡所属城市编码
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(
			String realName) {
		this.realName = realName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(
			String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(
			String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(
			String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(
			String bankCity) {
		this.bankCity = bankCity;
	}
	
}
