package com.wz.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.wz.cashloan.core.common.service.BaseService;
import com.wz.cashloan.system.domain.SysAccessCode;
import com.wz.cashloan.system.domain.SysUser;
import com.wz.cashloan.system.model.SysAccessCodeModel;


public interface SysAccessCodeService extends BaseService<SysAccessCode, Long>{
	/**
	 * 访问码信息列表查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<SysAccessCodeModel> listAccessCodeModel(Map<String, Object> params,
			int currentPage, int pageSize);
	
	/**
	 * 保存
	 * @param accessCode
	 * @param time
	 * @return
	 */
	int save(SysAccessCode accessCode, String time);
	
	/**
	 * 根据ID更新
	 * @param data
	 * @return
	 */
	int update(SysAccessCode ac,String time);
	
	/**
	 * 查询某用户code数量
	 * @param sysUserId
	 * @param code
	 * @return
	 */
	int countCode(long sysUserId, String code);
	
	/**
	 * 查询访问码列表
	 * @param sysUserId
	 * @return
	 */
	List<SysAccessCode> listSysAccessCode(Long sysUserId);
	
	/**
	 * 查询访问码
	 * @param map
	 * @return
	 */
	SysAccessCode findSysAccessCode(Map<String,Object> map);
	
	/**
	 * 更新访问码状态
	 * @param data
	 * @return
	 */
	int updateState(SysAccessCode ac);
	
	/**
	 * 用户名列表
	 * @return
	 */
	List<SysUser> listUserName();

	/**
	 * 访问码删除
	 */
	int deleteById(Long id);
}
