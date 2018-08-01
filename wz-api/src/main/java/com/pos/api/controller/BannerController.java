package com.pos.api.controller;

import com.czwx.cashloan.core.model.Banner;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.BannerService;
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
public class BannerController extends BaseController{
    @Resource
    private BannerService bannerService;

    /**
     * 轮播图列表
     * @param type 类型 1.首页 2.pos机
     */
    @RequestMapping("api/banner/list.htm")
    public void listBanner(@RequestParam(value = "type")String type){
        Map<String, Object> param = new HashMap<>();
        param.put("type",type);
        List<Banner> banners = bannerService.listSelect(param);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, banners);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
}
