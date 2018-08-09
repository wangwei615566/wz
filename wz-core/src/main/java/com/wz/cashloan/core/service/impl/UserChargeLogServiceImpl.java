package com.wz.cashloan.core.service.impl;

import com.wz.cashloan.core.mapper.UserChargeLogMapper;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.model.UserChargeLog;
import com.wz.cashloan.core.service.UserChargeLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("userChargeLogService")
public class UserChargeLogServiceImpl implements UserChargeLogService{
    @Resource
    private UserChargeLogMapper userChargeLogMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public void insertSelective(String zh,String je,String bz) {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("loginName",bz);
        User user = userMapper.findSelective(reqMap);
        if (user!=null){
            UserChargeLog userChargeLog = new UserChargeLog();
            userChargeLog.setAmount(BigDecimal.valueOf(Double.parseDouble(je)));
            userChargeLog.setRecAccount(zh);
            userChargeLog.setPayAccount(bz);
            userChargeLog.setState((byte)2);
            userChargeLog.setCreateTime(new Date());
            userChargeLog.setUpdateTime(new Date());
            userChargeLogMapper.insertSelective(userChargeLog);
        }

    }
}
