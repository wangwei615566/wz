package com.pos.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.common.util.ServletUtils;
import com.wz.cashloan.core.common.web.controller.BaseController;

@Scope("prototype")
@Controller
@RequestMapping("/api")
public class TestController extends BaseController{
	@RequestMapping("test")
	public void test(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, "");
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		JsonUtil.writeJson(result,response);
	}
}
