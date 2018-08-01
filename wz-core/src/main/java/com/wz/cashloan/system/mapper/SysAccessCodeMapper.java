package com.wz.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.system.domain.SysAccessCode;
import com.wz.cashloan.system.domain.SysUser;
import com.wz.cashloan.system.model.SysAccessCodeModel;


@RDBatisDao
public interface SysAccessCodeMapper extends BaseMapper<SysAccessCode,Long> {
	
	/**
	 * 访问码信息列表查询
	 * @param params
	 * return
	 */
	List<SysAccessCodeModel> listAccessCodeModel(Map<String, Object> params);

	/**
	 * 保存新增用户
	 * @param ac
	 * @return
	 */
	int save(SysAccessCode ac);
	
	/**
	 * 根据ID更新
	 * @param data
	 * @return
	 */
	int update(SysAccessCode ac);
	
	/**
	 * 查询某用户相同code数
	 * @param data
	 * @return
	 */
	int countCode(Map<String, Object> data);
	
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
	 * 用户名列表
	 * @return
	 */
	List<SysUser> listUserName();

	/**
	 * 访问码删除
	 */
	int deleteAccessCode(Long id);

	int deleteById(Long id);
}
