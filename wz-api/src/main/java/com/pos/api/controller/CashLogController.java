package com.pos.api.controller;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.service.UserCashLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 提现记录controller
 */
@Scope("prototype")
@Controller
@RequestMapping("/api")
public class CashLogController {
    @Resource
    private UserCashLogService userCashLogService;
    /**
     * 首页获取金额接口
     * @param reqMap
     * @param response
     */
    @RequestMapping("index/find/amount.htm")
    public void updateAmount(Map<String,Object> reqMap, HttpServletResponse response){
        Map<String, Object> result = new HashMap<>();
        int save = userCashLogService.save(reqMap);
        result.put(Constant.RESPONSE_CODE, save>0?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, save>0?"保存成功":"保存失败");
        JsonUtil.writeJson(result,response);
    }
}
