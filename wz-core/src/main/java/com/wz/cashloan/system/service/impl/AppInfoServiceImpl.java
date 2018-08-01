package com.wz.cashloan.system.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wz.cashloan.system.mapper.AppInfoMapper;
import com.wz.cashloan.system.service.AppInfoService;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

	@Resource
	private AppInfoMapper appInfoMapper;
	@Override
	public Map<String, Object> getAppInfo(String mobileType) {
		return appInfoMapper.getAppInfo(mobileType);
	}

}
