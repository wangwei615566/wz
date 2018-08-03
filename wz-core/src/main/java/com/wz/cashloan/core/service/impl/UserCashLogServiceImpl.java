package com.wz.cashloan.core.service.impl;

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
}
