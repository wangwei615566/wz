package com.wz.cashloan.system.service;

import java.util.Map;

/**
 * 获取app版本信息
 * @author wangwei
 *
 */
public interface AppInfoService {
	/**
	 * 获取app版本信息
	 * @param mobileType 1 ios 2 android
	 * @return
	 */
	Map<String,Object> getAppInfo(String mobileType);
}
