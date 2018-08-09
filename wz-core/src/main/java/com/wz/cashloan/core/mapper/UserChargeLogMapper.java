package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.UserChargeLog;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface UserChargeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserChargeLog record);

    int insertSelective(UserChargeLog record);

    UserChargeLog selectByPrimaryKey(Long id);

    List<UserChargeLog> listSelective(Map<String, Object> params);

    int updateOrder(Map<String, Object> params);

    int updateByPrimaryKeySelective(UserChargeLog record);

    int updateByPrimaryKey(UserChargeLog record);
}