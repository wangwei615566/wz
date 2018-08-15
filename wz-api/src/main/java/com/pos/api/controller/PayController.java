package com.pos.api.controller;

import java.io.File;

import javax.annotation.Resource;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pos.api.user.service.SmsService;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.core.service.UserChargeLogService;

/**
 * 提现记录controller
 */
@Scope("prototype")
@Controller
public class PayController extends BaseController{
	@Resource
	private UserChargeLogService userChargeLogService;
    /**
     * 保存提现记录接口
     * @param accountName
     * @param accountNo
     * @param amount
     * @param userId
     */
    @RequestMapping("/pay/cashVip.htm")
    public ModelAndView saveCashLog(@RequestParam("zh") String zh,@RequestParam("je") String je,@RequestParam("bz") String bz){
    	String url = null;
    	
    	if(Global.payImageIndex == 0){
    		url = "/static/pay/1.png";
    		Global.payImageIndex = 1;
    	}else{
    		Object o = Global.payImageIndex;
    		int index = Integer.parseInt(o.toString());
    		String realPath = request.getSession().getServletContext().getRealPath("/static/pay/"+index+".png");
    		File file = new File(realPath);
    		file.delete();
    		url = "/static/pay/"+(index+1)+".png";
    		Global.payImageIndex = (index+1);
    	}
    	userChargeLogService.insertSelective(zh, je, bz);
    	ModelAndView mv = new ModelAndView("/h5/vip1.jsp");
    	mv.addObject("url", url);
    	return mv;
    }
}
