package com.wz.cashloan.core.service;

import com.github.pagehelper.Page;
import com.wz.cashloan.core.model.UserChargeLog;

import java.util.Map;

public interface UserChargeLogService {

    void insertSelective(String zh,String je,String bz);

    Page<UserChargeLog> pageList(Map<String, Object> params, int current, int pageSize);

    int updateOrder(Map<String, Object> params);
}
