package com.pos.api.controller;

import com.czwx.cashloan.core.model.Banner;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.ConsultService;
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
public class ConsultController extends BaseController{
    @Resource
    private ConsultService consultService;

    /**
     * 咨询列表查询
     * @param type 类型 1.pos机 2.信用卡 3.网贷
     */
    @RequestMapping("api/consult/list.htm")
    public void listConsult(@RequestParam(value = "type")String type){
        Map<String, Object> param = new HashMap<>();
        param.put("type",type);
        List<Banner> data = consultService.listSelect(param);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
}
