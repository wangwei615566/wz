package com.wz.manage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.service.impl.BaseServiceImpl;
import com.wz.manage.domain.QuartzLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.manage.mapper.QuartzLogMapper;
import com.wz.manage.model.QuartzLogModel;
import com.wz.manage.service.QuartzLogService;


/**
 * 定时任务记录ServiceImpl
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-15 13:38:29
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@SuppressWarnings("unused")
@Service("quartzLogService")
public class QuartzLogServiceImpl extends BaseServiceImpl<QuartzLog, Long> implements QuartzLogService {
	
    
	private static final Logger logger = LoggerFactory.getLogger(QuartzLogServiceImpl.class);
   
    @Resource
    private QuartzLogMapper quartzLogMapper;




	@Override
	public BaseMapper<QuartzLog, Long> getMapper() {
		return quartzLogMapper;
	}




	@Override
	public int save(QuartzLog ql) {
		return quartzLogMapper.save(ql);
	}




	@Override
	public Page<QuartzLogModel> page(Map<String, Object> searchMap,
			int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<QuartzLogModel> list = quartzLogMapper.page(searchMap);
		return (Page<QuartzLogModel>)list;
	}
	
}