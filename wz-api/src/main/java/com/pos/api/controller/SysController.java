package com.pos.api.controller;

import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.util.CacheUtil;
import com.wz.cashloan.core.common.util.ServletUtils;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.system.permission.annotation.RequiresPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * API System Controller
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年4月29日 下午3:47:24
 * Copyright 杭州融都科技股份有限公司 All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */

@Controller
@Scope("prototype")
public class SysController extends BaseController {
    /**
     * 重加载系统配置数据
     * 
     * @throws Exception
     */
    @RequestMapping("/system/config/reload.htm")
    public void reload() throws Exception {
        // 调用缓存辅助类 重加载系统配置数据
        CacheUtil.initSysConfig();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, returnMap);
    }
    
}
