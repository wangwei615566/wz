package com.wz.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.system.domain.SysUser;
import com.wz.cashloan.system.domain.SysUserRole;

/**
 * 
 * 用户角色DAO
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:47:14
 */
@RDBatisDao
public interface SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {
	
	
	/**
	 * 根据用户ID删除
	 * @param userId 
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年9月22日
	 */
	void deleteByUserId(long userId);
	
	List<SysUserRole> getItemListByMap(Map<String, Object> param);
	/**
	 * 根据用户查询他的权限
	 * @param sysUser
	 * @return nid
	 */
	List<String> roleNameMap(SysUser sysUser);
}
