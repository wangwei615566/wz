package com.wz.manage.mapper;

import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.manage.domain.QuartzInfo;
import com.wz.manage.model.QuartzInfoModel;

import java.util.List;
import java.util.Map;


/**
 * 定时任务详情Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-15 13:30:53
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface QuartzInfoMapper extends BaseMapper<QuartzInfo, Long> {

	/**
	 * 定时任务查询
	 * 
	 * @param map
	 * @return
	 */
	List<QuartzInfoModel> page(Map<String, Object> map);
}
