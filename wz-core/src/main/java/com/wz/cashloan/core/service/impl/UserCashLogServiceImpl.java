package com.wz.cashloan.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.mapper.UserCashLogMapper;
import com.wz.cashloan.core.mapper.UserMapper;
import com.wz.cashloan.core.model.User;
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
    @Resource
    private UserMapper userMapper;
    @Override
    public Map<String, Object> save(UserCashLog userCashLog) {
        Double amount = userAmountService.getAmount(userCashLog.getUserId(), 0.0);
        Map<String, Object> result = new HashMap<>();
        if (userCashLog.getAmount().doubleValue()>amount || userCashLog.getAmount().doubleValue()<=0){
            result.put("code",-2);
            return result;
        }
        User user = userMapper.selectByPrimaryKey(userCashLog.getId());
        if (user.getVipState()==0 && userCashLog.getAmount().doubleValue()>0.5) {
            result.put("code",-3);
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
    public Page<Map<String,Object>> pageList(Map<String, Object> params, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<Map<String,Object>> orderList = userCashLogMapper.listSelective(params);
        return (Page<Map<String,Object>>) orderList;
    }

    @Override
    public int updateOrder(Map<String, Object> params) {
        UserCashLog userCashLog = userCashLogMapper.selectByPrimaryKey(Long.parseLong(params.get("id").toString()));
        String state = params.get("state").toString();
        if(!userCashLog.getState().equals(state)){
            //修改为失败
            if(state.equals("0")){
                userAmountService.getAmount(userCashLog.getUserId(), userCashLog.getAmount().doubleValue());
            } else {
                if (userCashLog.getState().equals("0")) {
                    userAmountService.getAmount(userCashLog.getUserId(), -userCashLog.getAmount().doubleValue());
                }
            }
        }
        return userCashLogMapper.updateOrder(params);
    }
    /**
     * 查询是否存在提现推荐记录
     * @param inviteId
     * @return
     */
    public boolean selectInviteId(Long inviteId){
    	return userCashLogMapper.selectInviteId(inviteId) > 0 ? true : false;
    }
}
