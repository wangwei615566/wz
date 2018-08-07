package com.wz.cashloan.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.mapper.UserCashLogMapper;
import com.wz.cashloan.core.model.UserCashLog;
import com.wz.cashloan.core.service.UserAmountService;
import com.wz.cashloan.core.service.UserCashLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userCashLogService")
public class UserCashLogServiceImpl implements UserCashLogService {
    @Resource
    private UserCashLogMapper userCashLogMapper;
    @Resource
    private UserAmountService userAmountService;
    @Override
    public Map<String, Object> save(UserCashLog userCashLog) {
        Double amount = userAmountService.getAmount(userCashLog.getUserId(), 0.0);
        Map<String, Object> result = new HashMap<>();
        if (userCashLog.getAmount().doubleValue()>amount || userCashLog.getAmount().doubleValue()<=0){
            result.put("code",-1);
            return result;
        }
        userAmountService.getAmount(userCashLog.getUserId(), -userCashLog.getAmount().doubleValue());
        int insert = userCashLogMapper.insert(userCashLog);
        result.put("code",insert>0?1:-1);
        result.put("amount", BigDecimal.valueOf(amount).subtract(userCashLog.getAmount()));
        return result;
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
