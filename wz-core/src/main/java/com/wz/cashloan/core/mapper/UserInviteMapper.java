package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.UserInvite;

import java.util.List;

@RDBatisDao
public interface UserInviteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInvite record);

    int insertSelective(UserInvite record);

    int countInvite(Long userId);

    List<String>listInviteName(Long userId);

    UserInvite selectByPrimaryKey(Long id);

    UserInvite findToInvite(Long id);

    int updateByPrimaryKeySelective(UserInvite record);

    int updateByPrimaryKey(UserInvite record);
}