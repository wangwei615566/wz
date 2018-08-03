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
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
     * 首页获取金额接口
     * @param cashWay
     * @param accountNo
     * @param amount
     * @param fee
     * @param userId
     */
    @RequestMapping("index/find/cashLog.htm")
    public void updateAmount(@RequestParam("cashWay") String cashWay,@RequestParam("accountNo") String accountNo,@RequestParam("amount") double amount,
    @RequestParam("fee") double fee,@RequestParam("userId") long userId){
        Map<String, Object> result = new HashMap<>();
        UserCashLog userCashLog = new UserCashLog(userId, Byte.parseByte(cashWay), accountNo, null, BigDecimal.valueOf(amount), BigDecimal.valueOf(fee), new Date());
        int save = userCashLogService.save(userCashLog);
        result.put(Constant.RESPONSE_CODE, save>0?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, save>0?"保存成功":"保存失败");
        JsonUtil.writeJson(result,response);
    }
}
