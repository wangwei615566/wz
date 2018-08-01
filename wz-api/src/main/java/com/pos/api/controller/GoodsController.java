package com.pos.api.controller;

import com.czwx.cashloan.core.model.Banner;
import com.czwx.cashloan.core.model.Goods;
import com.czwx.cashloan.core.model.GoodsDetail;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.GoodsService;
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
public class GoodsController extends BaseController {
    @Resource
    private GoodsService goodsService;

    /**
     * @param userId 用户id
     * @param type 1普通商品 2虚拟商品
     */
    @RequestMapping("api/goods/list.htm")
    public void goodsList(@RequestParam(value = "userId")Long userId,
                          @RequestParam(value = "type")String type){
        Map<String, Object> param = new HashMap<>();
        param.put("type",type);
        List<Goods> goods = goodsService.listGoods(type, userId);//profitAmount已经经过计算(即激活奖励金额)
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, goods);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * @param goodsId 商品id
     */
    @RequestMapping("api/goods/detail.htm")
    public void goodsDetail(@RequestParam(value = "goodsId")Long goodsId){
        Map<String, Object> param = new HashMap<>();
        param.put("goodsId",goodsId);
        List<GoodsDetail> goods = goodsService.listGoodsDetail(param);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, goods);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
}
