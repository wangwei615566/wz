package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.SysOperateLog;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;



@RDBatisDao
public interface SysManageOperateLogMapper extends BaseMapper<SysOperateLog,Long> {
  void saveSysManageOperateLog(SysOperateLog log);
}
