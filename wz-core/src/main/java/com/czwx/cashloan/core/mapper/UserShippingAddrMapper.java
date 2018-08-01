package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.UserShippingAddr;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;
@RDBatisDao
public interface UserShippingAddrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserShippingAddr record);

    int insertSelective(UserShippingAddr record);

    UserShippingAddr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserShippingAddr record);

    int updateByPrimaryKey(UserShippingAddr record);

    UserShippingAddr findSelective(Map<String,Object> param);

    List<UserShippingAddr> listSelective(Map<String,Object> param);

    UserShippingAddr findDefaultToUserId(Long userId);
}