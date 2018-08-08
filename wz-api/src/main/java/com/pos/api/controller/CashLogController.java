package com.pos.api.controller;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.core.model.UserCashLog;
import com.wz.cashloan.core.service.UserCashLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提现记录controller
 */
@Scope("prototype")
@Controller
@RequestMapping("/api")
public class CashLogController extends BaseController{
    @Resource
    private UserCashLogService userCashLogService;
    /**
     * 保存提现记录接口
     * @param accountName
     * @param accountNo
     * @param amount
     * @param userId
     */
    @RequestMapping("index/save/cashLog.htm")
    public void saveCashLog(@RequestParam("accountNo") String accountNo,@RequestParam("accountName") String accountName,@RequestParam("amount") double amount,
    @RequestParam("userId") long userId,@RequestParam("inviteId") long inviteId){
    	Map<String, Object> result = new HashMap<>();
    	if(inviteId != 0 && userCashLogService.selectInviteId(inviteId)){
    		result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_PARAM_INSUFFICIENT);
            result.put(Constant.RESPONSE_CODE_MSG, "该笔推荐奖励已申请提现，请勿重复操作");
            JsonUtil.writeJson(result,response);
    		return;
    	}
        UserCashLog userCashLog = new UserCashLog(userId, (byte)2, accountNo, accountName, BigDecimal.valueOf(amount), BigDecimal.valueOf(0), new Date());
        userCashLog.setState((byte)2);
        userCashLog.setInviteId(inviteId);
        Map<String, Object> rep = userCashLogService.save(userCashLog);
        int save = Integer.parseInt(rep.get("code").toString());
        result.put(Constant.RESPONSE_CODE, save==1?Constant.SUCCEED_CODE_VALUE : save==-2 ? Constant.FAIL_CODE_PARAM_INSUFFICIENT :Constant.FAIL_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA,rep.get("amount"));
        result.put(Constant.RESPONSE_CODE_MSG, save==1 ? "保存成功" : save==-2 ? "账户余额不足，无法提现":"保存失败");
        JsonUtil.writeJson(result,response);
    }

    /**
     * 查询提现记录接口

     * @param userId
     */
    @RequestMapping("index/list/cashLog.htm")
    public void listCashLog(@RequestParam("userId") long userId){
        Map<String, Object> result = new HashMap<>();
        List<UserCashLog> userCashLogs = userCashLogService.listUserCashLog(userId);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, userCashLogs);
        result.put(Constant.RESPONSE_CODE_MSG, "请求成功");
        JsonUtil.writeJson(result,response);
    }
}
