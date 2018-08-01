package com.pos.api.controller;

import com.czwx.cashloan.core.model.ProfitAmount;
import com.czwx.cashloan.core.model.ProfitCashLog;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.ProfitService;
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
public class ProfitController extends BaseController{

    @Resource
    private ProfitService profitService;
    /**
     * 用户的代理数及推广数接口
     * @param userId
     */
    @RequestMapping("api/profit/create/extensionAndGoodsCount.htm")
    public void extensionAndGoodsCount(@RequestParam(value = "userId")Long userId) {
        Map<String, Object> data = profitService.extensionAndGoodsCount(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
    /**
     * 用户的代理列表
     * @param userId
     */
    @RequestMapping("api/profit/create/extensionList.htm")
    public void extensionList(@RequestParam(value = "userId")Long userId) {
        List<Map<String, Object>> data = profitService.extensionList(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 用户的推广列表
     * @param userId
     */
    @RequestMapping("api/profit/create/goodsList.htm")
    public void goodsList(@RequestParam(value = "userId")Long userId) {
        List<Map<String, Object>> data = profitService.goodsDetaiUser(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 申请提现接口
     * @param userId
     */
    @RequestMapping("api/profit/apply/get/amount.htm")
    public void applyGetAmount(@RequestParam(value = "userId")long userId) {
        ProfitAmount data = profitService.findToUser(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 提现接口
     * @param userId
     */
    @RequestMapping("api/profit/get/amount.htm")
    public void getAmount(@RequestParam(value = "id")Long id,
                          @RequestParam(value = "userId")Long userId,
                          @RequestParam(value = "alipayNo")String alipayNo,
                          @RequestParam(value = "realName")String realName,
                          @RequestParam(value = "amount")double amount) {
        int i = profitService.getAmount(id,userId,alipayNo,realName,amount);
        Map<String, Object> result = new HashMap<String, Object>();
        if (i>0){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "支付成功");
        }else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "支付失败");
        }
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 提现记录
     * @param userId
     */
    @RequestMapping("api/profit/profitCashLog/list.htm")
    public void profitCashLogList(@RequestParam(value = "userId")Long userId) {
        List<ProfitCashLog> data = profitService.findProfitCashLog(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 今日及上个月收益统计
     * @param userId
     */
    @RequestMapping("api/profit/todayAndLastMont/amount/total.htm")
    public void todayAndLastMonthProFitTotal(@RequestParam(value = "userId")Long userId) {
        Map<String, Object> data = profitService.findIndex(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }
}
