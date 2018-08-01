package com.rongdu.cashloan.core.model;

import java.io.Serializable;

/**
 * 尾单合作页面显示model
 * @author taodd
 *
 */
public class SurplusOrderModel extends SurplusOrderPlatformModel{
	/**
	 * 平台id
	 */
	private Long id;
	/**
	 * 排名
	 */
	private Long ranking;
	/**
	 * 平台名称
	 */
	private String name;
	/**
	 * 平台介绍
	 */
	private String introduce;
	/**
	 * 今日PV
	 */
	private Long todayPV;
	/**
	 * 今日UV
	 */
	private Long todayUV;
	/**
	 * 总PV
	 */
	private Long totalPV;
	/**
	 * 总UV
	 */
	private Long totalUV;
	/**
	 * 状态 10 启用 20 禁用
	 */
	private String state;
	/**
	 * 状态对应中文名 10启用 20禁用
	 */
	private String stateStr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRanking() {
		return ranking;
	}
	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Long getTodayPV() {
		return todayPV;
	}
	public void setTodayPV(Long todayPV) {
		this.todayPV = todayPV;
	}
	public Long getTodayUV() {
		return todayUV;
	}
	public void setTodayUV(Long todayUV) {
		this.todayUV = todayUV;
	}
	public Long getTotalPV() {
		return totalPV;
	}
	public void setTotalPV(Long totalPV) {
		this.totalPV = totalPV;
	}
	public Long getTotalUV() {
		return totalUV;
	}
	public void setTotalUV(Long totalUV) {
		this.totalUV = totalUV;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/**启用*/
	public static final String STATE_ENABLE = "10";
	/**禁用*/
	public static final String STATE_DISABLE = "20";
	public String getStateStr() {
		if(STATE_ENABLE.equals(this.getState())){
			this.setStateStr("启用");
		}else if(STATE_DISABLE.equals(this.getState())){
			this.setStateStr("禁用");
		}else{
			this.setStateStr("--");
		}
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
	
}
