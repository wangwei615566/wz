package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.UserBaseInfo;

public interface UserBaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBaseInfo record);

    int insertSelective(UserBaseInfo record);

    UserBaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBaseInfo record);

    int updateByPrimaryKey(UserBaseInfo record);
}