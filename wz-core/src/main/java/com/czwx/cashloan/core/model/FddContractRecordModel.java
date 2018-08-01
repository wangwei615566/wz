package com.czwx.cashloan.core.model;

import java.util.Date;

public class FddContractRecordModel {
	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 合同下载地址
	 */
	private String download_url;
	
	/**
	 * 合同查看地址
	 */
	private String viewpdf_url;
	
	/**
	 * 生成时间
	 * @return
	 */
	private Date create_time;
	
	private int isdelete;
	
	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(
			Date create_time) {
		this.create_time = create_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(
			String download_url) {
		this.download_url = download_url;
	}

	public String getViewpdf_url() {
		return viewpdf_url;
	}

	public void setViewpdf_url(
			String viewpdf_url) {
		this.viewpdf_url = viewpdf_url;
	}
	
}
