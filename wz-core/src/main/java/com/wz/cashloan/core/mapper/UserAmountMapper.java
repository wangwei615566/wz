package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.UserAmount;
@RDBatisDao
public interface UserAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAmount record);

    int insertSelective(UserAmount record);

    UserAmount selectByPrimaryKey(Long id);

    UserAmount findByUserId(Long UserId);

    int updateByPrimaryKeySelective(UserAmount record);

    int updateByPrimaryKey(UserAmount record);
}