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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
@RequestMapping("/api")
public class InviteController extends BaseController{
    @Resource
    private UserInviteService userInviteService;

    /**
     * 推荐奖励查询
     * @param userId
     */
    @RequestMapping("index/reward/find.htm")
    public void rewardFind(@RequestParam("userId") long userId){
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> maps = userInviteService.listReward(userId);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, maps);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        JsonUtil.writeJson(result,response);
    }
}
