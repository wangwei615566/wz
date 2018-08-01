package com.pos.manage.controller;

import com.czwx.cashloan.core.model.Order;
import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.JsonUtil;
import com.rongdu.cashloan.core.common.util.RdPage;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.service.OrderService;
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
    private OrderService orderService;

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
        Page<Order> page = orderService.pageList(params, current, pageSize);
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, page.getResult());
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 查询单个订单
     * @param orderId 订单id
     * @throws Exception
     */
    @RequestMapping(value = "/manage/order/search/id.htm")
    public void findById(@RequestParam(value="orderId") long orderId) throws Exception {
        Order order = orderService.selectByPrimaryKey(orderId);
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, order);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 编辑订单
     * @param searchParams 修改参数，必须包含orderId
     * @throws Exception
     */
    @RequestMapping(value = "/manage/order/search/save.htm")
    public void save(@RequestParam(value="searchParams") String searchParams) throws Exception {
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        int code = orderService.updateBySelect(params);
        Map<String,Object> result = saveUpdateCommon(code, "保存失败", "保存成功");
        ServletUtils.writeToResponse(response, result);
    }
}
