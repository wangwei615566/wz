package com.wz.cashloan.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wz.cashloan.core.common.exception.ServiceException;
import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.service.impl.BaseServiceImpl;
import com.wz.cashloan.system.domain.SysUser;
import com.wz.cashloan.system.domain.SysUserRole;
import com.wz.cashloan.system.mapper.SysUserRoleMapper;
import com.wz.cashloan.system.service.SysUserRoleService;

@Service(value = "sysUserRoleServiceImpl")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {

	@Resource
	private SysUserRoleMapper sysUserRoleDao;
	
	@Override
	public List<SysUserRole> getSysUserRoleList(Long userId) throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return sysUserRoleDao.getItemListByMap(map);
	}


	@Override
	public BaseMapper<SysUserRole, Long> getMapper() {
		return sysUserRoleDao;
	}


	@Override
	public List<String> roleNameMap(SysUser sysUser) throws ServiceException {
		return sysUserRoleDao.roleNameMap(sysUser);
	}
}
