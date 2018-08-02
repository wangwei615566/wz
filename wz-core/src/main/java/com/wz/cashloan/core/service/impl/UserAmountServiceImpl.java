package com.wz.cashloan.core.service.impl;

import com.wz.cashloan.core.mapper.UserAmountMapper;
import com.wz.cashloan.core.model.UserAmount;
import com.wz.cashloan.core.service.UserAmountService;
import org.springframework.stereotype.Service;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 用户资金表service
 */
@Service("userAmountService")
public class UserAmountServiceImpl implements UserAmountService {
    @Resource
    private UserAmountMapper userAmountMapper;

    /**
     * 首页获取金额方法
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public Double getAmount(Long userId, Double amount) {
        UserAmount userAmount = userAmountMapper.findByUserId(userId);
        double resAmount = BigDecimalUtil.add(userAmount.getTotal().doubleValue(), amount);
        userAmount.setTotal(BigDecimal.valueOf(resAmount));
        userAmountMapper.updateByPrimaryKey(userAmount);
        return resAmount;
    }
}
