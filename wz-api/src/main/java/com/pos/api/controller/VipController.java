package com.pos.api.controller;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.common.web.controller.BaseController;
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
 * vip的controller
 */
@Scope("prototype")
@Controller
@RequestMapping("/api")
public class VipController extends BaseController{
    @Resource
    private UserInviteService userInviteService;

    /**
     * 免费开通vip接口
     * @param userId
     * @param userId
     */
    @RequestMapping("/index/free/vip.htm")
    public void freeVip(@RequestParam("userId") Long userId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> freeInvite = userInviteService.getFreeInvite(userId);
        int code = (int) freeInvite.get("state");
        result.put(Constant.RESPONSE_CODE,code == -1 ? Constant.FAIL_CODE_PARAM_INSUFFICIENT : Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, code == -1 ? freeInvite.get("msg") : "开通成功");
        JsonUtil.writeJson(result,response);
    }
}
