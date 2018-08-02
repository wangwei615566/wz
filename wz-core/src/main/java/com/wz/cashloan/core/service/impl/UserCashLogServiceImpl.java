package com.wz.cashloan.core.service.impl;

import com.wz.cashloan.core.mapper.UserCashLogMapper;
import com.wz.cashloan.core.model.UserCashLog;
import com.wz.cashloan.core.service.UserCashLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service("userCashLogService")
public class UserCashLogServiceImpl implements UserCashLogService {
    @Resource
    private UserCashLogMapper userCashLogMapper;
    @Override
    public int save(Map<String,Object> map) {
        Byte cashWay = Byte.parseByte(map.get("cashWay").toString());
        String accountNo = map.get("accountNo").toString();
        Double amount = map.get("amount")==null?0:Double.parseDouble(map.get("amount").toString());
        Double fee = map.get("fee") == null ?0.0:Double.parseDouble(map.get("fee").toString());
        Long userId = Long.parseLong(map.get("user_id").toString());
        UserCashLog userCashLog = new UserCashLog(userId, cashWay, accountNo, null, BigDecimal.valueOf(amount), BigDecimal.valueOf(fee), new Date());
        return userCashLogMapper.insert(userCashLog);
    }
}
