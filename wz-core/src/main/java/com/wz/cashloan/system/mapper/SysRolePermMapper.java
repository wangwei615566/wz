
package com.wz.cashloan.system.mapper;

import com.wz.cashloan.core.common.mapper.BaseMapper;
import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.system.domain.SysRolePerm;

@RDBatisDao
public interface SysRolePermMapper extends BaseMapper<SysRolePerm, Long>{

    SysRolePerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePerm record);
    
    int deleteByRoleId(Integer roleId);
    
}