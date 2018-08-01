package com.wz.cashloan.system.mapper;

import java.util.Map;

import com.wz.cashloan.core.common.mapper.RDBatisDao;

/**
 * 获取app版本信息
 * @author wangwei
 *
 */
@RDBatisDao
public interface AppInfoMapper {

	/**
	 * 获取app版本信息
	 * @param mobileType 1 ios 2 android
	 * @return
	 */
	Map<String,Object> getAppInfo(String mobileType);
}
