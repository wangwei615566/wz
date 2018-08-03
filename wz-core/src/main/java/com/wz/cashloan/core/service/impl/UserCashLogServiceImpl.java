package com.wz.cashloan.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.mapper.UserCashLogMapper;
import com.wz.cashloan.core.model.UserCashLog;
import com.wz.cashloan.core.service.UserCashLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userCashLogService")
public class UserCashLogServiceImpl implements UserCashLogService {
    @Resource
    private UserCashLogMapper userCashLogMapper;
    @Override
    public int save(UserCashLog userCashLog) {
        return userCashLogMapper.insert(userCashLog);
    }

    @Override
    public List<UserCashLog> listUserCashLog(Long userId) {
        return userCashLogMapper.listToUserId(userId);
    }

    @Override
    public Page<UserCashLog> pageList(Map<String, Object> params, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<UserCashLog> orderList = userCashLogMapper.listSelective(params);
        return (Page<UserCashLog>) orderList;
    }

    @Override
    public int updateOrder(Map<String, Object> params) {
        return userCashLogMapper.updateOrder(params);
    }
}
