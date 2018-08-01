package com.rongdu.cashloan.core.model;

import java.io.Serializable;

public class OverdueCollectionUserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 联系人姓名
	 */
	private String contactName;
	
//	/**
//	 * 联系人与客户关系
//	 */
//	private String contactRelation;
	
//	/**
//	 * 联系人工作单位
//	 */
//	private String contactWorkUnit;
//	
//	/**
//	 * 联系人单位电话
//	 */
//	private String contactUnitPhone;
	
	/**
	 * 联系人手机号码
	 */
	private String contactPhone;
	
//	/**
//	 * 联系人住宅电话
//	 */
//	private String contactHomePhone;
//	
//	/**
//	 * 联系人现居地址
//	 */
//	private String contactCurrAddress;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
}
