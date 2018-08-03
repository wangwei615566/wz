package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.UserCashLog;

import java.util.List;

@RDBatisDao
public interface UserCashLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCashLog record);

    int insertSelective(UserCashLog record);

    UserCashLog selectByPrimaryKey(Long id);

    List<UserCashLog> listToUserId(Long userId);

    int updateByPrimaryKeySelective(UserCashLog record);

    int updateByPrimaryKey(UserCashLog record);
}