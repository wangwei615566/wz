package com.pos.api.controller;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.core.service.UserAmountService;
import com.wz.cashloan.core.service.UserInviteService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页controller
 */
@Scope("prototype")
@Controller
@RequestMapping("/api")
public class IndexController extends BaseController{
    @Resource
    private UserAmountService userAmountService;
    @Resource
    private UserInviteService userInviteService;
    /**
     * 首页获取金额接口
     * @param userId
     * @param amount
     */
    @RequestMapping("index/find/amount.htm")
    public void updateAmount(@RequestParam("userId") Long userId,@RequestParam("amount") Double amount){
        Map<String, Object> result = new HashMap<String, Object>();
        Double reqAmount = userAmountService.getAmount(userId, amount);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, reqAmount);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        JsonUtil.writeJson(result,response);
    }

    /**
     * 分享页面查询推广人数
     * @param userId
     * @param response
     */
    @RequestMapping("index/extension/count.htm")
    public void extensionCount(@RequestParam("userId") Long userId,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        int extensionCount = userInviteService.getExtensionCount(userId);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, extensionCount);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        JsonUtil.writeJson(result,response);
    }

    /**
     * 分享页面查询注册人数
     * @param userId
     * @param response
     */
    @RequestMapping("index/register/count.htm")
    public void registerCount(@RequestParam("userId") Long userId,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        int count = userInviteService.registerCount(userId);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, count);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        JsonUtil.writeJson(result,response);
    }

    /**
     * 参数配置表参数返回
     */
    @RequestMapping("index/sys/config.htm")
    public void sysConfig(){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("cftNumber",Global.getValue("cft_number"));
        data.put("vipAmount",Global.getValue("vip_amount"));
        data.put("extensionCount",Global.getValue("extension_count"));
        data.put("inviteCount",Global.getValue("invite_count"));
        data.put("vipDesc",Global.getValue("vip_desc"));
        data.put("notice",Global.getValue("notice"));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        JsonUtil.writeJson(result,response);
    }
}
