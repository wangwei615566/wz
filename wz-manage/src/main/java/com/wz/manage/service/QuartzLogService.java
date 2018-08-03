package com.wz.manage.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wz.manage.domain.QuartzLog;
import com.wz.manage.model.QuartzLogModel;
import com.wz.cashloan.core.common.service.BaseService;

/**
 * 定时任务记录Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-15 13:38:29
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface QuartzLogService extends BaseService<QuartzLog, Long> {

	/**
	 * 保存日志
	 */
	int save(QuartzLog ql);

	Page<QuartzLogModel> page(Map<String, Object> searchMap, int current,
                              int pageSize);

}
