package com.wz.manage.controller;

import com.github.pagehelper.Page;
import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.util.JsonUtil;
import com.wz.cashloan.core.common.util.RdPage;
import com.wz.cashloan.core.common.util.ServletUtils;
import com.wz.cashloan.core.model.UserCashLog;
import com.wz.cashloan.core.service.UserCashLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class ManageOrderController extends ManageBaseController {

    @Resource
    private UserCashLogService userCashLogService;

    /**
     * 订单列表
     * @param searchParams 查询参数 json字符串
     * @param current 当前页
     * @param pageSize 每页页数
     * @throws Exception 异常
     */
    @RequestMapping(value = "/manage/order/search/list.htm")
    public void searchList(@RequestParam(value="searchParams",required=false) String searchParams,
                           @RequestParam(value = "current") int current,
                           @RequestParam(value = "pageSize") int pageSize) throws Exception {
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Page<Map<String,Object>> page = userCashLogService.pageList(params, current, pageSize);
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, page.getResult());
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 保存单个订单
     * @param searchParams
     * @throws Exception
     */
    @RequestMapping(value = "/manage/order/search/save.htm")
    public void findById(@RequestParam(value="searchParams",required=true) String searchParams) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        int i = userCashLogService.updateOrder(params);
        if(i == 1){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "成功");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "失败");
        }
        ServletUtils.writeToResponse(response, result);
    }

}
