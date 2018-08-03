package com.wz.cashloan.core.mapper;

import java.util.List;
import java.util.Map;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateBySelective(Map<String,Object> map);

    int updateByPrimaryKey(User record);

    User findSelective(Map<String,Object> param);

    List<User> listSelective(Map<String,Object> param);

    Map<String,Object>findLevelToUserId(Long userId);
}