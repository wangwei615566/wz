package com.rongdu.cashloan.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 尾单平台实体类
 * @author taodd
 *
 */
public class SurplusOrderPlatformModel implements Serializable{
	private static final long serialVersionUID = 1L;
	/**启用*/
	public static final String STATE_ENABLE = "10";
	/**禁用*/
	public static final String STATE_DISABLE = "20";
	private Long id;//id，主键
	private String name;//平台名称
	private String channelUrl;//渠道网址
	private String introduce;//平台介绍
	private Long ranking;//排名
	private String state;// 状态 10启用，20禁用
	private Date creatTime;//创建时间
	private Date updateTime;//更新时间
	
	private Long platformId;//id，主键 为区分
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
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
		this.name = name;
	}
	
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Long getRanking() {
		return ranking;
	}
	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
