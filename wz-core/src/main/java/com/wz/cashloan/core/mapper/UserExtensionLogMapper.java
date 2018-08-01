package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.model.UserExtensionLog;

public interface UserExtensionLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserExtensionLog record);

    int insertSelective(UserExtensionLog record);

    UserExtensionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserExtensionLog record);

    int updateByPrimaryKey(UserExtensionLog record);
}