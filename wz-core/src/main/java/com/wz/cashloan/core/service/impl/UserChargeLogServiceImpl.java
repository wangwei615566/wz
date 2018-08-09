package com.wz.cashloan.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.mapper.UserChargeLogMapper;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
import com.wz.cashloan.core.model.UserCashLog;
import com.wz.cashloan.core.model.UserChargeLog;
import com.wz.cashloan.core.service.UserAmountService;
import com.wz.cashloan.core.service.UserChargeLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userChargeLogService")
public class UserChargeLogServiceImpl implements UserChargeLogService{
    @Resource
    private UserChargeLogMapper userChargeLogMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAmountService userAmountService;
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

    @Override
    public Page<UserChargeLog> pageList(Map<String, Object> params, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<UserChargeLog> orderList = userChargeLogMapper.listSelective(params);
        return (Page<UserChargeLog>) orderList;
    }

    @Override
    public int updateOrder(Map<String, Object> params) {
        UserChargeLog userChargeLog = userChargeLogMapper.selectByPrimaryKey(Long.parseLong(params.get("id").toString()));
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("loginName",userChargeLog.getPayAccount());
        User user = userMapper.findSelective(reqMap);
        String state = params.get("state").toString();
        if(!userChargeLog.getState().equals(state)){
            //修改为失败
            if(state.equals("0")){
                userAmountService.getAmount(user.getId(), userChargeLog.getAmount().doubleValue());
            } else {
                if (userChargeLog.getState().equals("0")) {
                    userAmountService.getAmount(user.getId(), -userChargeLog.getAmount().doubleValue());
                }
            }
        }
        return userChargeLogMapper.updateOrder(params);
    }
}