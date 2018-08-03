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
     * @param response
     */
    @RequestMapping("/free/vip.htm")
    public void freeVip(@RequestParam("userId") long userId){
        Map<String, Object> result = new HashMap<String, Object>();
        boolean freeInvite = userInviteService.getFreeInvite(userId);
        result.put(Constant.RESPONSE_CODE,freeInvite?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_PARAM_INSUFFICIENT);
        result.put(Constant.RESPONSE_CODE_MSG, freeInvite?"开通成功":"开通失败");
        JsonUtil.writeJson(result,response);
    }
}
