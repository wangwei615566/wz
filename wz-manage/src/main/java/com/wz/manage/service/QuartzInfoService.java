package com.wz.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.wz.manage.domain.QuartzInfo;
import com.wz.manage.model.QuartzInfoModel;
import com.wz.cashloan.core.common.service.BaseService;

/**
 * 定时任务详情Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-15 13:30:53
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface QuartzInfoService extends BaseService<QuartzInfo, Long> {


	/**
	 * 保存定时任务数据
	 * @param qi
	 */
	boolean save(QuartzInfo qi);

	/**
	 * 修改定时任务
	 * @param search
	 * @return
	 */
	boolean update(Map<String, Object> search);

	/**
	 * 查询所有任务
	 * @param result
	 * @return
	 */
	List<QuartzInfo> list(Map<String, Object> result);

	/**
	 * 定时任务分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<QuartzInfoModel> page(Map<String, Object> searchMap, int current,
                               int pageSize);
	
	/**
	 * 据任务标识查询任务
	 * @param paramMap
	 * @return
	 */
	QuartzInfo findByCode(String code);
	
	/**
	 * 据条件查询定时任务详情
	 * @param paramMap
	 * @return
	 */
	QuartzInfo findSelective(Map<String, Object> paramMap);
	
}
