package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.model.UserCashLog;

public interface UserCashLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCashLog record);

    int insertSelective(UserCashLog record);

    UserCashLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCashLog record);

    int updateByPrimaryKey(UserCashLog record);
}