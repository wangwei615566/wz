package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.model.UserInvite;

public interface UserInviteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInvite record);

    int insertSelective(UserInvite record);

    UserInvite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInvite record);

    int updateByPrimaryKey(UserInvite record);
}