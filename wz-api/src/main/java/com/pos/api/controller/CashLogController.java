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
    @RequestParam("userId") long userId){
        Map<String, Object> result = new HashMap<>();
        UserCashLog userCashLog = new UserCashLog(userId, (byte)2, accountNo, accountName, BigDecimal.valueOf(amount), BigDecimal.valueOf(0), new Date());
        userCashLog.setState((byte)2);
        Map<String, Object> rep = userCashLogService.save(userCashLog);
        int save = Integer.parseInt(rep.get("code").toString());
        result.put(Constant.RESPONSE_CODE, save>0?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA,rep.get("amount"));
        result.put(Constant.RESPONSE_CODE_MSG, save>0?"保存成功":"保存失败");
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
