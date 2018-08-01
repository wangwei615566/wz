package com.wz.cashloan.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wz.cashloan.core.common.exception.PersistentDataException;
import com.wz.cashloan.core.common.exception.ServiceException;
import com.wz.cashloan.system.domain.SysRoleMenu;
import com.wz.cashloan.system.mapper.SysRoleMenuMapper;
import com.wz.cashloan.system.service.SysRoleMenuService;

@Service(value = "sysRoleMenuServiceImpl")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Resource
	private SysRoleMenuMapper sysRoleMenuDao;
	
	@Override
	public List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException, PersistentDataException {
		return this.sysRoleMenuDao.getRoleMenuList(roleId);
	}

	public SysRoleMenuMapper getSysRoleMenuDao() {
		return sysRoleMenuDao;
	}

	public void setSysRoleMenuDao(SysRoleMenuMapper sysRoleMenuDao) {
		this.sysRoleMenuDao = sysRoleMenuDao;
	}



}
