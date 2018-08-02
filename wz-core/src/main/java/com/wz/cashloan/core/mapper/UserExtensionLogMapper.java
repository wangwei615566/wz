package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.UserExtensionLog;
@RDBatisDao
public interface UserExtensionLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserExtensionLog record);

    int insertSelective(UserExtensionLog record);

    int countIp(Long userId);

    UserExtensionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserExtensionLog record);

    int updateByPrimaryKey(UserExtensionLog record);
}