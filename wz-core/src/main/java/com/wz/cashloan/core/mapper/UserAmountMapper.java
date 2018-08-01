package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.model.UserAmount;

public interface UserAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAmount record);

    int insertSelective(UserAmount record);

    UserAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAmount record);

    int updateByPrimaryKey(UserAmount record);
}