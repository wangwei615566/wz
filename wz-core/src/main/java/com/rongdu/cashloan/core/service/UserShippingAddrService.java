package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.UserShippingAddr;

import java.util.List;
import java.util.Map;

public interface UserShippingAddrService {
    List<UserShippingAddr> listSelective(Map<String,Object> param);

    int saveOrUpdate(UserShippingAddr userShippingAddr);

    int deleteByPrimaryKey(Long id);

    UserShippingAddr findDefaultToUserId(Long userId);
}
