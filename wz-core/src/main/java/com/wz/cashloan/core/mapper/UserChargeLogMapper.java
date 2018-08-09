package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.UserChargeLog;
@RDBatisDao
public interface UserChargeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserChargeLog record);

    int insertSelective(UserChargeLog record);

    UserChargeLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserChargeLog record);

    int updateByPrimaryKey(UserChargeLog record);
}